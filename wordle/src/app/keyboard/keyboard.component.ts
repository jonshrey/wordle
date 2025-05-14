// keyboard.component.ts
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-keyboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './keyboard.component.html',
  styleUrls: ['./keyboard.component.css']
})
export class KeyboardComponent {
  @Output() keyPress = new EventEmitter<string>();

  // This will receive the letter states from parent
  @Input() letterStates: Record<string, 'correct' | 'present' | 'absent'> = {};

  keys = [
    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'],
    ['ENTER', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'BACKSPACE']
  ];

  getKeyClass(key: string): string {
    if (key.length > 1) return ''; // Skip for ENTER/BACKSPACE

    const state = this.letterStates[key.toUpperCase()];
    return state || '';
  }

  onKeyClick(key: string): void {
    this.keyPress.emit(key);
  }
}
