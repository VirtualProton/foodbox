import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/Service/category.service';
import { SearchService } from 'src/app/Service/search.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  category:any=[];
  constructor(
    private categoryService:CategoryService,
    private searchService: SearchService
    ) { }

  ngOnInit(): void {
    this.getCategory()
  }
  getCategory(){
    this.categoryService.fetchCategory({location:"hyderabad"}).subscribe(
      (res)=>{
        this.category = res.body.data;
      }
    )
  }

  focusFood(val:any){
    console.log(val)
    document.getElementById(val)?.scrollIntoView();
    
    // console.log("here"+ document.getElementById(val));
  }
}
