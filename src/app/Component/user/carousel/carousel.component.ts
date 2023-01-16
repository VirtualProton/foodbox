import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {
  public banner: any = [];
  public bannerSize: any;
  constructor() { }

  ngOnInit(): void {
    this.banner = [{
      image: 'assets/banner/banner.png',
      thumbImage: 'assets/banner/banner.png',
      alt: 'alt of image',
    }, {
      image: 'assets/banner/banner.png', // Support base64 image
      thumbImage: 'assets/banner/banner.png', // Support base64 image
      alt: 'Image alt', //Optional: You can use this key if want to show image with alt
      order: 1 //Optional: if you pass this key then slider images will be arrange according @input: slideOrderType
    },{
      image: 'assets/banner/banner.png',
      thumbImage: 'assets/banner/banner.png',
      alt: 'alt of image',
    },{
      image: 'assets/banner/banner.png',
      thumbImage: 'assets/banner/banner.png',
      alt: 'alt of image',
    },{
      image: 'assets/banner/banner.png',
      thumbImage: 'assets/banner/banner.png',
      alt: 'alt of image',
    },{
      image: 'assets/banner/banner.png',
      thumbImage: 'assets/banner/banner.png',
      alt: 'alt of image',
    },{
      image: 'assets/banner/banner.png',
      thumbImage: 'assets/banner/banner.png',
      alt: 'alt of image',
    },{
      image: 'assets/banner/banner.png',
      thumbImage: 'assets/banner/banner.png',
      alt: 'alt of image',
    },
    ];
    this.bannerSize = {
      with: "556px",
      height: "230px",
      space: 10
    }
  }

}
