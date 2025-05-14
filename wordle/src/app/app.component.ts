import { Component, computed, inject, signal, viewChildren } from '@angular/core';
import { GuessInputComponent } from "./guess-input/guess-input.component";
import { WordleServiceService } from './wordle-service.service';
import { GuessResponse } from './types';
import { RegistrationFormComponent } from "./registration-form/registration-form.component";
import { KeyboardComponent } from './keyboard/keyboard.component';

@Component({
  selector: 'app-root',
  imports: [GuessInputComponent, RegistrationFormComponent, KeyboardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'wordle';
  initialGuess = ["", "", "", "", ""];
  historicGuesses = ["", "", "", "", "", ""];
  historicFeedbacks = signal<GuessResponse[]>([]);
  userId = signal<string>("");
  username = signal<string>("Anonymous");
  isGameCreated = signal<boolean>(false);
  inputs = viewChildren(GuessInputComponent);
  errorMessage = signal<string>("");

  // Track the state of each letter for keyboard coloring
  letterStates = signal<Record<string, 'correct' | 'present' | 'absent'>>({});

  private wordleService = inject(WordleServiceService);

  getSplitFeedbackFor(attemptNumber: number) {
    if (this.historicFeedbacks()[attemptNumber]) {
      return this.historicFeedbacks()[attemptNumber].feedback.split("");
    } else {
      return ["", "", "", "", ""];
    }
  }

  async submitGuess(guess: string, idx: number) {
    try {
      const serviceResponse = await this.wordleService.submitGuess(this.userId(), guess);
      console.log(`Got this from the service: ${serviceResponse.message}`);

      // Update feedback history
      this.historicFeedbacks.update((oldContents) => [...oldContents, serviceResponse]);
      this.historicGuesses[idx] = guess;

      // Update keyboard letter states
      this.updateLetterStates(guess, serviceResponse.feedback);

      // Move focus to next input if available
      if (idx < this.inputs().length - 1) {
        this.inputs()[idx + 1].focusToFirstInput();
      }
    } catch (error) {
      this.errorMessage.set(error as string);
    }
  }

  private updateLetterStates(guess: string, feedback: string): void {
    const newStates = {...this.letterStates()};

    for (let i = 0; i < guess.length; i++) {
      const letter = guess[i].toUpperCase();
      const feedbackChar = feedback[i];

      // Only upgrade the state (correct > present > absent)
      if (!newStates[letter] ||
          (feedbackChar === 'G' && newStates[letter] !== 'correct') ||
          (feedbackChar === 'Y' && newStates[letter] === 'absent')) {

        newStates[letter] =
          feedbackChar === 'G' ? 'correct' :
          feedbackChar === 'Y' ? 'present' : 'absent';
      }
    }

    this.letterStates.set(newStates);
  }

  async register(username: string) {
    this.username.set(username);
    try {
      const registerResponse = await this.wordleService.register(username);
      console.log(`Register response: ${registerResponse.message}`);
      this.userId.set(registerResponse.id);
    } catch (err) {
      this.errorMessage.set(err as string);
    }
  }

  async createNewGame() {
    try {
      const createResponse = await this.wordleService.create(this.userId());
      if (createResponse.message) {
        this.isGameCreated.set(true);
        // Reset states for new game
        this.historicFeedbacks.set([]);
        this.historicGuesses = ["", "", "", "", "", ""];
        this.letterStates.set({});
      }
    } catch (error) {
      this.errorMessage.set(error as string);
    }
  }

  handleKeyPress(key: string) {
    const currentInputIdx = this.historicGuesses.findIndex(g => g === '');
    if (currentInputIdx === -1) return;

    const currentInput = this.inputs()[currentInputIdx];

    if (key === 'BACKSPACE') {
      // Handle backspace - clear the last filled letter
      for (let i = 4; i >= 0; i--) {
        if (currentInput.guessLetters[i]().trim() !== '') {
          currentInput.guessLetters[i].set('');
          break;
        }
      }
    } else if (key === 'ENTER') {
      // Handle enter - submit if guess is complete
      const guess = currentInput.guessLetters.map(sig => sig()).join('');
      if (guess.length === 5) {
        this.submitGuess(guess, currentInputIdx);
      }
    } else if (key.length === 1 && /^[A-Za-z]$/.test(key)) {
      // Handle letter input - fill the next empty box
      const upperKey = key.toUpperCase();
      for (let i = 0; i < 5; i++) {
        if (currentInput.guessLetters[i]().trim() === '') {
          currentInput.guessLetters[i].set(upperKey);
          break;
        }
      }
    }
  }
}
