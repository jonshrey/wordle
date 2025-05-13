import { Component, ElementRef, input, output, Signal, ViewChild, viewChild } from '@angular/core';
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
  disabledInTypeScript = false;
  form: Signal<ElementRef | undefined> = viewChild('test')
  
  submitGuess(): void {
    this.wordInputted.emit(this.guessLetters.map(sig => sig()).join("")); 
    this.disabledInTypeScript = true;
  }

  focusToFirstInput() {
    var f = this.form();
    if (f) {
      console.log("hello");
      var firstInput = f.nativeElement.children[0] as HTMLInputElement;
      firstInput.focus();
    } 
  }
  
  focusToNextInput(event: Event) {
    var elem = event.target as HTMLElement;
    console.log(`Hello from ${elem}`);
    if (elem.nextElementSibling && elem.nextElementSibling instanceof HTMLInputElement) {
      (elem.nextElementSibling as HTMLInputElement).focus();
    }
  }
}
