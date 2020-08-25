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
export class EntryPageComponent implements OnInit {
  selected: "";
  constructor(private entryService: EntryService, private router: Router) { }

  ngOnInit(): void {
  }

  addNewDrink(form: NgForm){
    if(form.invalid){
      console.log("Drink Form is invalid");
      return;
    }

    console.log("Drink Form contents: \n" + form.value.drinkName
     + "\n" + form.value.price + "\n" + form.value.type
      + "\n" + form.value.size);

    this.entryService.addDrink(form.value.drinkName, form.value.price, form.value.type, form.value.size)
      .subscribe((drink: Drink) => this.router.navigate(['/menu']));
  }

  addNewFood(form: NgForm){
    if(form.invalid){
      console.log("Food Form is invalid");
      return;
    }
    console.log("Food Form contents: \n" + form.value.foodName
     + "\n" + form.value.price + "\n" + form.value.review);

    this.entryService.addFood(form.value.foodName, form.value.price, form.value.review)
     .subscribe((food: Food) => this.router.navigate(['/menu']));
  }
}
