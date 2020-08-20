import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EntryService } from 'src/app/entry.service';
import { Router } from '@angular/router';

import Drink from 'src/app/models/drink';
import Food from 'src/app/models/food';

@Component({
  selector: 'app-entry-page',
  templateUrl: './entry-page.component.html',
  styleUrls: ['./entry-page.component.scss']
})
// Can't resolve all parameters for EntryPageComponent in c:/Users/Powerkilroy/Desktop/GitRepo/Learning---Random/StarbuckLogger/frontend/src/app/pages/entry-page/entry-page.component.ts: ([object Object], [object Object], ?).ng
export class EntryPageComponent implements OnInit {
  selected: "";
  constructor(private entryService: EntryService, private router: Router/*, private newDrink: Drink*/) { }

  ngOnInit(): void {
  }

  addNewDrink(form: NgForm){
    if(form.invalid){
      console.log("Drink Form is invalid");
      return;
    }

    // Might not be needed...
    /*this.newDrink.drink = form.value.drink;
    this.newDrink.price = form.value.price;
    this.newDrink.type = form.value.type;
    this.newDrink.size = form.value.size;*/

    console.log("Drink Form contents: \n" + form.value.drink
     + "\n" + form.value.price + "\n" + form.value.type
      + "\n" + form.value.size);

    // Might be able to just pass in the Form and have the DB
    // handle the populating part for the drink.
    this.entryService.addDrink(form.value.drink, form.value.price, form.value.type, form.value.size)
      .subscribe((drink: Drink) => this.router.navigate(['/menu']));
  }

  addNewFood(form: NgForm){
    if(form.invalid){
      console.log("Food Form is invalid");
      return;
    }
    console.log("Food Form contents: \n" + form.value.food
     + "\n" + form.value.price + "\n" + form.value.review);

    this.entryService.addFood(form.value.food, form.value.price, form.value.review)
     .subscribe((food: Food) => this.router.navigate(['/menu']));
  }
}
