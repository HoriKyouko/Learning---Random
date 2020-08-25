import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EntryService } from 'src/app/entry.service';
import Drink from 'src/app/models/drink';
import Food from 'src/app/models/food';

@Component({
  selector: 'app-table-view',
  templateUrl: './table-view.component.html',
  styleUrls: ['./table-view.component.scss']
})
export class TableViewComponent implements OnInit {
  selected: "";

  drinksArray: Drink[] = [];
  foodsArray: Food[] = [];

  constructor(private http: HttpClient, private entryService: EntryService) { }

  ngOnInit(): void {
    this.getDrinkData();
    this.getFoodData();
  }

  getDrinkData(){
    this.entryService.getDrink().subscribe((drinks: Drink[]) => this.drinksArray = drinks);
    console.log(this.drinksArray);
  }

  getFoodData(){
    this.entryService.getFood().subscribe((foods: Food[]) => this.foodsArray = foods);
  }
}
