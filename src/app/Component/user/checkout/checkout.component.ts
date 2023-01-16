import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrderService } from 'src/app/Service/order.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  public orderForm: any;
  private userName = localStorage.getItem('userName');
  private selectedLocation = localStorage.getItem('selectedLocation');
  private totalPrice: any;
  private localCart: any;
  private orderedItem:any
  @ViewChild('successMsg', { read: TemplateRef })
  msgModel!: TemplateRef<any>

  constructor(private _fb: FormBuilder,
    private orderService: OrderService,
    private router: Router,
    private modelSevice: NgbModal) { }

  ngOnInit(): void {
    this.initilizeOrderForm();
    // console.log(this.fullprice)
    this.getTotalprice();
    this.getOrderedItem();
  }

  initilizeOrderForm() {
    this.orderForm = this._fb.group({
      fullName: [null, Validators.required],
      contact: [this.userName, Validators.required],
      email: [null],
      address: [null, Validators.required],
      city: [this.selectedLocation, Validators.required]
    })
  }
  getTotalprice() {
    this.totalPrice = 0;
    this.localCart = JSON.parse(localStorage.getItem("localCart") as string);
    for (let i = 0; i < this.localCart.length; i++) {
      // console.log(this.localCart[i]);
      this.totalPrice = this.totalPrice + this.localCart[i].price * this.localCart[i].quantity
    }

    console.log(this.totalPrice);

  }
  getOrderedItem() {
    this.orderedItem =""
    this.localCart = JSON.parse(localStorage.getItem("localCart") as string);
    for (let i = 0; i < this.localCart.length; i++) {
      // console.log(this.localCart[i]);
      this.orderedItem = this.orderedItem + this.localCart[i].foodName+" X"+this.localCart[i].quantity+", ";
    }

    // console.log(this.orderedItem);

  }
  placeOrder() {
    console.log(this.orderForm.value)
    let data = {
      location: this.orderForm.value.city,
      orderedItem: this.orderedItem,
      userName: this.userName,
      customerName: this.orderForm.value.fullName,
      contact: this.orderForm.value.contact,
      email: this.orderForm.value.email,
      fullAddress: this.orderForm.value.address + "," + this.orderForm.value.city,
      status: "1",
      totalPrice: this.totalPrice,
      transcId: "transcId"
    }
    this.orderService.addOrder(data).subscribe(
      (res) => {
        if (res.status == 200) {
          this.openModel(this.msgModel);
        } else if (res.status == 400) {
          console.log(res.body.error)
        }
      }
    )

  }

  goBack(){
    this.router.navigateByUrl("/user")
  }
  get f1() {
    return this.orderForm.controls
  }



  openModel(ref: any) {
    this.modelSevice.open(ref, {
      size: 'md',
      backdrop: 'static',
      keyboard: false,
      windowClass: 'custom-model'
    });
  }
  closeModel() {
    this.modelSevice.dismissAll();
  }

  closePopup(){
    localStorage.removeItem("localCart");
    this.router.navigateByUrl("http://localhost:4200/user")
    this.closeModel();
  }
}
