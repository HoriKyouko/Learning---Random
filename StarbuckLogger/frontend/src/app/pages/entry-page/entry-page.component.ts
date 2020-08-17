import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-entry-page',
  templateUrl: './entry-page.component.html',
  styleUrls: ['./entry-page.component.scss']
})

export class EntryPageComponent implements OnInit {
  selected: "";
  constructor() { }

  ngOnInit(): void {
  }

  addNewDrink(form: NgForm){
    if(form.invalid){
      console.log("Drink Form is invalid");
      return;
    }
    console.log("Drink Form contents: \n" + form.value.drink
     + "\n" + form.value.price + "\n" + form.value.type
      + "\n" + form.value.size);
  }

  addNewFood(form: NgForm){
    if(form.invalid){
      console.log("Food Form is invalid");
      return;
    }
    console.log("Food Form contents: \n" + form.value.food
     + "\n" + form.value.price + "\n" + form.value.review);
  }
}
