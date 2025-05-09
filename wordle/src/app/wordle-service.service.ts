import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WordleServiceService {

  constructor() { }

  submitGuess(guess: string) : void{
    console.log(`One day I will connect to the backend and an actual request will be made. For now pretend that I did submit ${guess}`);
  }
}
