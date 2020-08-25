import { Injectable } from '@angular/core';
import { WebService } from './web.service';

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  constructor(private webService: WebService) { }

  addDrink(drinkName: string, price: number, type: string, size: string){
    return this.webService.post('drink', {drinkName, price, type, size});
  }

  addFood(foodName: string, price: number, review: string){
    return this.webService.post('food', {foodName, price, review});
  }

  getDrink(){
    return this.webService.get('drink');
  }
  getFood(){
    return this.webService.get('food');
  }
}
