import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/Service/order.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.scss']
})
export class OrderHistoryComponent implements OnInit {
  public orderHistory:any=[];
  private userName:any = localStorage.getItem('userName');
  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
    this.getAllOrders()
  }
  getAllOrders(){
    this.orderService.fetchUserOrder({userName:this.userName}).subscribe(
      (res)=>{
        if(res.status == 200){
          this.orderHistory = res.body.data;
        }
      }
    )
  }
}
