import { Component, input, output } from '@angular/core';
import {signal} from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-guess-input',
  imports: [FormsModule],
  templateUrl: './guess-input.component.html',
  styleUrl: './guess-input.component.css'
})
export class GuessInputComponent {
  initialGuess = input<string>();
  guess = signal("");
  wordInputted = output<string>();

  logGuessToConsole(): void {
    console.log(this.guess());
  }

  submitGuess(): void {
    this.wordInputted.emit(this.guess()); 
  }

}
