import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { ServiceTourismEntity } from 'src/app/model/service-tourism-entity';
import { AlertService } from 'src/app/services/external/alert.service';
import { ModalServiceComponent } from 'src/app/services/external/modal-service/modal-service.component';
import { ServiceTourismService } from 'src/app/services/personalized/service-tourism.service';

@Component({
  selector: 'app-sale-service',
  templateUrl: './sale-service.component.html',
  styleUrls: ['./sale-service.component.css']
})
export class SaleServiceComponent  implements OnInit{
  protected serviceTourismEntityList:ServiceTourismEntity[];

  constructor(private serviceTourismService:ServiceTourismService, 
    private alertService:AlertService, 
    private appComponent:AppComponent,
    private modalServiceComponent:ModalServiceComponent){}
  ngOnInit(): void {
    this.loadData();
  }

  /**
   * this method load all data
   */
  private loadData():void{
    this.serviceTourismService.getListEntity().subscribe(data=>this.serviceTourismEntityList=data);
  }

  /**
   * method where modal info
   * @param code this id of the ServiceTourismEntity
   */
  protected viewModalServiceInfo(code:string):void{
    this.modalServiceComponent.getModalServiceTourism .viewModal(code);
  }
  //method where car store
  protected addSaleServiceTourism(id:string):void{
    AppComponent.addCodeServiceList(id, this.serviceTourismService);
    this.alertService.alertInfo("adding service to shopping cart");
  }
}
