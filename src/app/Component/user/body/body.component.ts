import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { CategoryService } from 'src/app/Service/category.service';
import { FoodService } from 'src/app/Service/food.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SearchService } from 'src/app/Service/search.service';
import { CartService } from 'src/app/Service/cart.service';
import { LocationService } from 'src/app/Service/location.service';
@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.scss']
})
export class BodyComponent implements OnInit {
  public categories: any = []
  public productModel: any;
  public foods: any = [];
  public searchText: any;
  public Cart: any;
  public selectedLocation:any = "hyderabad";
  @ViewChild('myMofel', { read: TemplateRef })
  myModel!: TemplateRef<any>
  constructor(
    private categoryService: CategoryService,
    private foodService: FoodService,
    private modelSevice: NgbModal,
    private searchService: SearchService,
    private cartService: CartService,
    private locationService:LocationService
  ) {
    this.searchService.searchSubject.subscribe(
      (data) => {
        this.searchText = data;
        // console.log(this.searchText)
      }
    )

    this.locationService.selectedLocation.subscribe(
      (location)=>{
        this.selectedLocation = location;
        console.log(this.selectedLocation)
        this.getCategory(this.selectedLocation);
        this.getFoods(this.selectedLocation)
      }
    )
  }

  ngOnInit(): void {
    // console.log(this.searchText)
    this.getCategory(this.selectedLocation);
    this.getFoods(this.selectedLocation);
  //  this.disabledButton();
  }
  getCategory(obj:any) {
    this.categoryService.fetchCategory({ location: obj }).subscribe(
      (res) => {
        this.categories = res.body.data
      }
    )
  }
  getFoods(obj:any) {
    this.foodService.fetchFood({ location: obj }).subscribe(
      (res) => {
        this.foods = res.body.data;
        this.disabledButton();
      }
    )
    //this.disabledButton();
  }

  addToCart(product: any) {
    // this.disabledButton();
    let cartDataNull = localStorage.getItem("localCart");
    if (cartDataNull == null) {
      product.quantity = 1;
      let storeDataGet: any = [];
      storeDataGet.push(product);
      localStorage.setItem('localCart', JSON.stringify(storeDataGet))
    } else {
      this.Cart = JSON.parse(localStorage.getItem("localCart") as string);
      for (let i = 0; i < this.Cart.length; i++) {
        if (this.Cart[i].id == product.id) {
          this.Cart[i].quantity++
          localStorage.setItem('localCart', JSON.stringify(this.Cart));
         return;
        }
      }
      product.quantity = 1;
      this.Cart.push(product);
      localStorage.setItem('localCart', JSON.stringify(this.Cart))
    }
    this.cartCountFun();
  }
  cartCountFun() {
    var cartValue = JSON.parse(localStorage.getItem('localCart') as string);
    this.cartService.cartSubject.next(cartValue);
  }

  openModel(ref: any) {
    this.modelSevice.open(ref, {
      size: 'lg',
      backdrop: 'static',
      keyboard: false,
      windowClass: 'custom-model'
    });
  }

  openProductsModel(data: any, ref: any) {
    this.productModel = data
    this.openModel(ref)
  }

  closeModel() {
    this.modelSevice.dismissAll();
  }

  disabledButton(){
    this.Cart = JSON.parse(localStorage.getItem("localCart") as string);
    for (let i = 0; i < this.Cart.length; i++) {
      for (let j = 0; j < this.foods.length; j++){
        if (this.Cart[i].id == this.foods[j].id) {
          let btn =document.getElementById('40289dd98554a61a018554a9b6030003');
          btn?.hidden;
          
          
        }
      }
    }
  }
}
