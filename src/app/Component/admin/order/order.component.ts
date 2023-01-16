import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/Service/order.service';
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  public Orders:any=[]
  location = localStorage.getItem("storeLocation");
  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
    this. fectOrderDetail();
  }

  fectOrderDetail(){
    this.orderService.fetchLocationOrder({location:this.location}).subscribe(
      (res)=>{
        this.Orders = res.body.data;
      }
    )
  }

}
