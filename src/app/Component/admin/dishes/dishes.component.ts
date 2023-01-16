import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UploadfileService } from 'src/app/Service/uploadfile.service';
import { FoodService } from 'src/app/Service/food.service';
// import { ModalService } from '@coreui/angular';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CategoryService } from 'src/app/Service/category.service';
@Component({
  selector: 'app-dishes',
  templateUrl: './dishes.component.html',
  styleUrls: ['./dishes.component.scss']
})
export class DishesComponent implements OnInit {
  public dishesDetailsForm: any;
  public dishes: any = [];
  public categories: any = [];
  public deleteObj: any;
  public statusMsg: any;
  public searchText: any;
  public formData: FormData = new FormData()
  public img: any = "";
  private id: any;
  private isFileSelected:any= false;
  private file!: File;
  p: number = 1;
  public fileToUpload: File | null = null;
  editFormFlag: any = false;
  @ViewChild('alertModal', { static: false })
  alertModal!: ElementRef;
  @ViewChild('confirmModal', { static: false })
  confirmModal!: ElementRef;
  @ViewChild('myModal', { read: TemplateRef })
  myModal!: TemplateRef<any>;
  constructor(
    private _fb: FormBuilder,
    public router: Router,
    public uploadfileService: UploadfileService,
    public foodService:FoodService,
    public categoryService:CategoryService,
    private modalService:NgbModal
  ) { }

  ngOnInit(): void {
    this.initilizeForm();
    this.getAllCategory();
    this.getAlldishes();
  }
  initilizeForm() {
    this.dishesDetailsForm = this._fb.group({
      // location: [null, Validators.required],
      foodName: [null, Validators.required],
      discription: [null, Validators.required],
      type: [0, Validators.required],
      category: ["", Validators.required],
      isAvialable: [true, Validators.required],
      price: ["", Validators.required],
      // imgUrl: ["", Validators.required],
    })

  }
  getAlldishes(){
    this.foodService.fetchFood({ location: "hyderabad" }).subscribe(
      (res) => {
        this.dishes = res.body.data;
      }
    )
  }

  getAllCategory(){
    this.categoryService.fetchCategory({ location: "hyderabad" }).subscribe(
      (res)=>{
        this.categories = res.body.data;
      }
    )
  }
  onselectfile(e: any) {
    
    this.file = e.target.files[0];
    this.formData.append("file", this.file);
    let v = this.formData.get("file")
    this.isFileSelected = true
    // console.log(v);
  }

  uploadFlie(): void {
    
    this.uploadfileService.uploadFile(this.formData).subscribe(
      (res) => {
        // console.log(res.body.data);
      let  fileUrl = res.body
        this.img = fileUrl.url;

        if(!this.editFormFlag){
          this.addDishes();
        }else if(this.editFormFlag){
          this.updateProductDetails();
        }  
      }
    )
  }

  addDishes() {
    // this.uploadFlie()
    // console.log(this.dishesDetailsForm.value)
    let data = {
      location: 'hyderabad',
      foodName: this.dishesDetailsForm.value.foodName,
      discription: this.dishesDetailsForm.value.discription,
      type:this.dishesDetailsForm.value.type,
      category: this.dishesDetailsForm.value.category,
      isAvialable: this.dishesDetailsForm.value.isAvialable,
      price: this.dishesDetailsForm.value.price,
      imgUrl:this.img
     
    }

    // console.log(data);
    this.foodService.addFood(data).subscribe(
      (res)=>{
        this.getAlldishes();
        this.closeModal();
        this.resetForm();
        this.isFileSelected ==false;
      }
    )
  }

  openModal(ref:any){
    this.modalService.open(ref,{
      backdrop:'static',
      keyboard:false,
      // windowClass:'custom-modal'
    });
  }

  openFrom(mod:any,refobj?:any, ref?:any){
    this.img="";
    this.editFormFlag= false;
    this.openModal(ref)
    if(mod=="edit"){
      this.img = refobj.imgUrl;
      this.id = refobj.id;
      this.editFormFlag=true
      this.dishesDetailsForm.patchValue(refobj)
    }
  }

  closeModal(){
    this.modalService.dismissAll();
    this.resetForm();
  }

  resetForm(){
    this.dishesDetailsForm.reset()
    this.editFormFlag=false;
  }

  get f(){
    return this.dishesDetailsForm.controls
  }


  submitData(){
    if(this.isFileSelected == false){
      // console.log("f");
       this.addDishes();
    }else if(this.isFileSelected == true){
      this.uploadFlie()
      // this.file = undefined;
    }
  }

  

  deleteProduct(e:any){
    this.foodService.deleteFood(e).subscribe(
      (res)=>{
        if(res.status ==200){
          this.getAlldishes();
        }
      }
    )
  }

  submitEditData(e:any){
    if(this.isFileSelected == false){
     
       this.updateProductDetails();
    }else if(this.isFileSelected == true){
      this.uploadFlie() 
     
    }
  }
  updateProductDetails(){   
    let data = {
      id:this.id,
      location: 'hyderabad',
      foodName: this.dishesDetailsForm.value.foodName,
      discription: this.dishesDetailsForm.value.discription,
      type:this.dishesDetailsForm.value.type,
      category: this.dishesDetailsForm.value.category,
      isAvialable: this.dishesDetailsForm.value.isAvialable,
      price: this.dishesDetailsForm.value.price,
      imgUrl:this.img    
    } 

  
    this.foodService.updateFood(data).subscribe(
      (res)=>{
        if(res.status==200){
          this.statusMsg = "Product updated successfully";
          this.getAlldishes();
          this.closeModal();
          this.resetForm();
          this.isFileSelected ==false;
          //this.openModal(this.alertModal)
        }
      },(err)=>{
        this.statusMsg = err.error.message;
        this.closeModal();
        this.resetForm();
        //this.openModal(this.alertModal)
      }
    )
  }
}
