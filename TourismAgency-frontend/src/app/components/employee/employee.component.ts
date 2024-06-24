import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { AppComponent } from 'src/app/app.component';
import { ImageEntity } from 'src/app/model/image-entity';
import { PackageTourismEntity } from 'src/app/model/package-tourism-entity';
import { ServiceTourismEntity } from 'src/app/model/service-tourism-entity';
import { AlertService } from 'src/app/services/external/alert.service';
import { ImageService } from 'src/app/services/external/image.service';
import { ModalServiceComponent } from 'src/app/services/external/modal-service/modal-service.component';
import { LoginService } from 'src/app/services/personalized/login.service';
import { PackageTourismService } from 'src/app/services/personalized/package-tourism.service';
import { ServiceTourismService } from 'src/app/services/personalized/service-tourism.service';
import { Utils } from 'src/app/util/oter/utils';

@Component({
  selector: 'app-customer',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeComponent implements OnInit {
  protected titleModal: string = "";
  //stringListSubject: BehaviorSubject<PackageTourismEntity[]> = new BehaviorSubject<PackageTourismEntity[]>([]);

  protected serviceTourismEntityList: BehaviorSubject<ServiceTourismEntity[]> = new BehaviorSubject<ServiceTourismEntity[]>([]);
  protected packageTourismEntityList: BehaviorSubject<PackageTourismEntity[]> = new BehaviorSubject<PackageTourismEntity[]>([]);
  protected listServiceOfPackage = new Set<string>();

  protected serviceTourismForm = new FormGroup({
    codeService: new FormControl(this.code("S")),
    name: new FormControl('', [Validators.required]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    description: new FormControl(),
    dateService: new FormControl(Utils.LocalDate),
    image: new FormControl(),
    available: new FormControl(true),
  });
  protected packageTourismForm = new FormGroup({
    codePackage: new FormControl(this.code("P")),
    namePackage: new FormControl('', [Validators.required]),
    cost: new FormControl(0, [Validators.required, Validators.min(1)]),
    services: new FormControl(this.listServiceOfPackage),
    available: new FormControl(true),
  });

  constructor(
    private loginService: LoginService,
    private router: Router,
    private alertService: AlertService,
    private serviceTourismService: ServiceTourismService,
    private packageTourismService: PackageTourismService,
    private modalServiceComponent: ModalServiceComponent,
    private imageService: ImageService) { }

  ngOnInit(): void {
    this.isEmployee.subscribe(
      value => {
        if (!value) {
          this.acctionSecurity();
        }
      }
    )
    this.serviceTourismService.getListEntity("all").subscribe(list => this.serviceTourismEntityList.next(list));
    this.packageTourismService.getListEntity("all").subscribe(list => this.packageTourismEntityList.next(list));
  }
  protected get isCustomer() {
    return AppComponent.isCustomer$;
  }
  protected get isEmployee() {
    return AppComponent.isEmployee$;
  }
  protected get isOwner() {
    return AppComponent.isOwner$;
  }
  protected get isAdministrator() {
    return AppComponent.isAdministrator$;
  }

  protected clickButtonInfoServiceTourism(code: string): void {
    this.modalServiceComponent.getModalServiceTourism.viewModal(code);
  }

  protected loadImage() {
    const input = document.getElementById('file') as HTMLInputElement;
    const preview = document.getElementById('preview') as HTMLImageElement;

    if (input.files && input.files[0]) {
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        if (e.target && typeof e.target.result === 'string') {
          preview.src = e.target.result;
        }
      };
      reader.readAsDataURL(input.files[0]);
    } else {
      preview.src = '';
    }
  }


  protected newPackageTourism() {
    this.listServiceOfPackage = new Set<string>();
    this.packageTourismForm = new FormGroup({
      codePackage: new FormControl(this.code("P")),
      namePackage: new FormControl('', [Validators.required]),
      cost: new FormControl(0, [Validators.required, Validators.min(1)]),
      services: new FormControl(this.listServiceOfPackage),
      available: new FormControl(true),
    });
  }
  protected newServiceTourism() {
    this.titleModal = "New"
    this.serviceTourismForm = new FormGroup({
      codeService: new FormControl(this.code("S")),
      name: new FormControl('', [Validators.required]),
      price: new FormControl(0, [Validators.required, Validators.min(1)]),
      description: new FormControl(),
      dateService: new FormControl(Utils.LocalDate),
      image: new FormControl(),
      available: new FormControl(true),
    });
  }
  protected updateServiceTourism(codeService: string) {
    this.titleModal = "Update";

    this.serviceTourismService.getEntity(codeService).subscribe(service => {
      this.serviceTourismForm = new FormGroup({
        codeService: new FormControl(service.codeService),
        name: new FormControl(service.nameService, [Validators.required]),
        price: new FormControl(service.costService, [Validators.required, Validators.min(1)]),
        description: new FormControl(service.description),
        dateService: new FormControl(Utils.LocalDate),
        image: new FormControl(service.urlImage),
        available: new FormControl(service.available),
      });
    },
      error => this.acctionSecurity(error)
    );
  }
  protected updatePackageTourism(codeService: string) {
    this.titleModal = "Update";

    this.packageTourismService.getEntity(codeService).subscribe(packag => {
      this.listServiceOfPackage = new Set<string>(packag.services);
      this.packageTourismForm = new FormGroup({
        codePackage: new FormControl(packag.codePackage),
        namePackage: new FormControl(packag.namePackage, [Validators.required]),
        cost: new FormControl(packag.cost, [Validators.required, Validators.min(1)]),
        services: new FormControl(this.listServiceOfPackage),
        available: new FormControl(packag.available),
      });
    },
      error => this.acctionSecurity(error)
    );
  }
  protected async addServiceTourismEntity() {
    let entity: ServiceTourismEntity = new ServiceTourismEntity();
    entity.codeService = this.serviceTourismForm.value.codeService + "";
    entity.nameService = this.serviceTourismForm.value.name + "";
    entity.urlImage = null;
    entity.description = this.serviceTourismForm.value.description + "";
    entity.dateService = this.serviceTourismForm.value.dateService + "";
    entity.costService = parseFloat(this.serviceTourismForm.value.price + "");
    entity.available = (this.serviceTourismForm.value.available == null) ? false : this.serviceTourismForm.value.available;
    this.serviceTourismService.cretate(entity, this.loginService.Token);
    this.serviceTourismService.getListEntity("all").subscribe(list => this.serviceTourismEntityList.next(list));
    this.alertService.alertInfo("Successful in service", 1600, "green");
  }
  protected async addPackageTourismEntity() {
    let entity: PackageTourismEntity = new PackageTourismEntity();
    this.listServiceOfPackage = new Set<string>();
    entity.codePackage = this.packageTourismForm.value.codePackage + "";
    entity.namePackage = this.packageTourismForm.value.namePackage + "";
    entity.cost = parseFloat(this.packageTourismForm.value.cost + "");
    entity.services = this.packageTourismForm.value.services == null ? [] : Array.from(this.packageTourismForm.value.services);
    entity.available = this.packageTourismForm.value.available == null ? false : this.packageTourismForm.value.available;
    this.packageTourismService.cretate(entity, this.loginService.Token);
    this.packageTourismService.getListEntity("all").subscribe(list => this.packageTourismEntityList.next(list));
    this.alertService.alertInfo("Successful in package", 1600, "green");
  }

  protected get localDate() {
    return Utils.LocalDate;
  }

  private code(start: string) {
    let code = start;
    for (let i = 0; i < 4; i++) {
      code += (Math.round(Math.random() * 9));
    }

    return code;
  }

  private acctionSecurity(error: string = "") {
    console.error("no login " + error);
    this.alertService.alertInfo("Not authorized", 1600, "red")
    this.router.navigate(['/home']);
  }

  protected removeListServiceOfPackage(code: string) {
    if(this.listServiceOfPackage.size>1){
      let deletedService=this.listServiceOfPackage.delete(code);
    this.packageTourismService.getListEntity("all").subscribe(list => this.packageTourismEntityList.next(list));
      if(deletedService){
        this.alertService.alertInfo("successful remove item", 1600, "green");
      }else{
        console.error("invalid code");
        this.alertService.alertInfo("Error, try it again", 1600, "red");
      }
    }else{
      this.alertService.alertInfo("Error, you must have least one item", 1600, "red");
    }
  }
  protected addingListServiceOfPackage(code: string){
    this.listServiceOfPackage.add(code);
    this.packageTourismService.getListEntity("all").subscribe(list => this.packageTourismEntityList.next(list));
    this.alertService.alertInfo("adding item", 1600, "green");
  }
}