import { Component, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { PackageTourismEntity } from 'src/app/model/package-tourism-entity';
import { AlertService } from 'src/app/services/external/alert.service';
import { ModalServiceComponent } from 'src/app/services/external/modal-service/modal-service.component';
import { PackageTourismService } from 'src/app/services/personalized/package-tourism.service';

@Component({
  selector: 'app-sale-package',
  templateUrl: './sale-package.component.html',
  styleUrls: ['./sale-package.component.css']
})
export class SalePackageComponent implements OnInit{
  protected packageTourismEntityList:PackageTourismEntity[];

  constructor(private packageTourismService:PackageTourismService,
    private modalServiceComponent:ModalServiceComponent,
    private alertService:AlertService
  ){}
  ngOnInit(): void {
    this.loadData();
  }

  private loadData():void{
    this.packageTourismService.getListEntity().subscribe(data=>this.packageTourismEntityList=data);
  }

  protected viewModalServiceInfo(code:string):void{
    this.modalServiceComponent.getModalServiceTourism.viewModal(code);
  }
  protected addSalePackageTorism(code:string):void{
    AppComponent.addCodePackageList(code, this.packageTourismService);
    this.alertService.alertInfo("adding Package to shopping cart");
  }
}
