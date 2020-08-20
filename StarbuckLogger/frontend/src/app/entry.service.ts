import { Injectable } from '@angular/core';
import Drink from './models/drink';
import { WebService } from './web.service';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  constructor(private webService: WebService) { }

  addDrink(drink: string, price: number, type: string, size: string){
    return this.webService.post('drink', {drink, price, type, size});
  }

  addFood(food: string, price: number, review: string){
    return this.webService.post('food', {food, price, review});
  }
}
