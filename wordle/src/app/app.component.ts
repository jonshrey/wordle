import { Component, inject } from '@angular/core';
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
  initialGuess = 'HOUSE';

  private wordleService = inject(WordleServiceService);

  submitGuess(guess: string) {
    this.wordleService.submitGuess(guess);
  }
}
