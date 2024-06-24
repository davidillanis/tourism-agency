import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{
  private index:number=0;

  ngOnInit(): void {
    this.changedImageBackground();
    setInterval(() => {
      this.changedImageBackground();
    }, 5000);
  }

  changedImageBackground() {
    const images = [
      "https://concepto.de/wp-content/uploads/2012/03/turismo-e1552499811477.jpg",
      "https://www.comexperu.org.pe/upload/images/hechos_turismo-receptivo-140723-020639.jpg",
      "https://unsm.edu.pe/wp-content/uploads/2017/01/turismo-portada.jpg",
      "https://webunwto.s3.eu-west-1.amazonaws.com/2023-09/international-tourism-swiftly-overcoming-pandemic-downturn.jpg"
    ];
    const container = document.querySelector(".container") as HTMLElement;
    container.style.backgroundImage = `url(${images[this.index]})`;

    this.index=images.length==this.index+1? 0:this.index+1;
  }
}
