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
  initialGuess = input<string[]>(["", "", "", "", ""]);
  guessLetters = [signal(""), signal(""), signal(""), signal(""), signal("")];
  wordInputted = output<string>();

  submitGuess(): void {
    this.wordInputted.emit(this.guessLetters.map(sig => sig()).join("")); 
  }

}
