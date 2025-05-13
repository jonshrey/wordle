import { Component, inject, viewChildren } from '@angular/core';
import { CounterComponent } from "./counter/counter.component";
import { GuessInputComponent } from "./guess-input/guess-input.component";
import { WordleServiceService } from './wordle-service.service';

@Component({
  selector: 'app-root',
  imports: [CounterComponent, GuessInputComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'wordle';
  initialGuess = ["", "", "", "", ""];
  historicGuesses = ["", "", "", "", "", ""];
  inputs = viewChildren(GuessInputComponent);

  private wordleService = inject(WordleServiceService);

  submitGuess(guess: string, idx: number) {
    this.wordleService.submitGuess(guess);
    this.historicGuesses[idx] = guess;
    if (idx < this.inputs().length - 1)
      this.inputs()[idx + 1].focusToFirstInput();
  }
}
