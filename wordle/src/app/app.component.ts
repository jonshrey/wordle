import { Component, computed, inject, signal, viewChildren } from '@angular/core';
import { GuessInputComponent } from "./guess-input/guess-input.component";
import { WordleServiceService } from './wordle-service.service';
import { GuessResponse } from './types';
import { RegistrationFormComponent } from "./registration-form/registration-form.component";

@Component({
  selector: 'app-root',
  imports: [GuessInputComponent, RegistrationFormComponent],
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

  private wordleService = inject(WordleServiceService);

  getSplitFeedbackFor(attemptNumber: number) {
    if (this.historicFeedbacks()[attemptNumber]) {
      return this.historicFeedbacks()[attemptNumber].feedback.split("");
    } else {
      return ["", "", "", "", ""];
    }
  }

  async submitGuess(guess: string, idx: number) {
    var serviceResponse = await this.wordleService.submitGuess(this.userId(), guess);
    console.log(`Got this from the service: ${serviceResponse.message}`);
    this.historicFeedbacks.update((oldContents) => [...oldContents, serviceResponse]);
    this.historicGuesses[idx] = guess;
    if (idx < this.inputs().length - 1)
      this.inputs()[idx + 1].focusToFirstInput();
  }

  async register(username: string) {
    this.username.set(username);
    try {
      var registerResponse = await this.wordleService.register(username);
      console.log(`This is the register response that I received: ${registerResponse.message}`);
      this.userId.set(registerResponse.id);
    } catch (err) {
      this.errorMessage.set(err as string);
    }

  }

  async createNewGame() {
    var createResponse = await this.wordleService.create(this.userId());
    if (createResponse.message)
      this.isGameCreated.set(true);
  }
}
