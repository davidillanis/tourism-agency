import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { PackageTourismEntity } from './model/package-tourism-entity';
import { SaleEntity } from './model/sale-entity';
import { ServiceTourismEntity } from './model/service-tourism-entity';
import { AlertService } from './services/external/alert.service';
import { ModalServiceComponent } from './services/external/modal-service/modal-service.component';
import { CustomerService } from './services/personalized/customer.service';
import { LoginService } from './services/personalized/login.service';
import { PackageTourismService } from './services/personalized/package-tourism.service';
import { SaleService } from './services/personalized/sale.service';
import { ServiceTourismService } from './services/personalized/service-tourism.service';
import { KeycloakService } from './services/personalized/keycloak.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  //Shopping Cart
  protected static saleServiceEntityList: ServiceTourismEntity[] = [];
  protected static salePackageEntityList: PackageTourismEntity[] = [];

  //Shopping Sale
  protected saleEntityList:SaleEntity[]=[];
  protected saleEntity:SaleEntity=new SaleEntity();

  protected username: string;
  protected isLoggedIn$: Observable<boolean>;

  public static isCustomer$:Observable<boolean>;
  public static isEmployee$:Observable<boolean>;
  public static isOwner$:Observable<boolean>;
  public static isAdministrator$:Observable<boolean>;


  constructor(
    private loginService: LoginService,
    private alertService: AlertService,
    private saleService: SaleService,
    private customerService:CustomerService,
  private modalSevice: ModalServiceComponent
  ) {
  }
  ngOnInit(): void {
    //load data username and isLogged
    this.isLoggedIn$ = this.loginService.isLoggedIn();
    this.username = this.loginService.Username;

    //load roles
    AppComponent.isCustomer$ = this.hasRole("CUSTOMER");
    AppComponent.isEmployee$ = this.hasRole("EMPLOYEE");
    AppComponent.isOwner$ = this.hasRole("OWNER");
    AppComponent.isAdministrator$=this.hasRole("ADMINISTRATOR");

    //load data services shopping cart
    const storedServiceData = sessionStorage.getItem("saleServiceEntityList");
    if (storedServiceData) {
      AppComponent.saleServiceEntityList = JSON.parse(storedServiceData) as ServiceTourismEntity[];
    }
    //load data package shopping cart
    const storedPackageData = sessionStorage.getItem("salePackageEntityList");
    if (storedPackageData) {
      AppComponent.salePackageEntityList = JSON.parse(storedPackageData) as PackageTourismEntity[];
    }

    //load data in sales
    AppComponent.isCustomer$.subscribe(data=>{
      if(data){
        this.saleService.getListEntity(this.loginService.Token).subscribe(
          data=>{
            //this.saleEntityList=data
            data.forEach(sale=>{
              this.customerService.findIdByUsername(this.loginService.Username, this.loginService.Token).subscribe(
                idCustomer=>{
                  if(idCustomer==sale.idCustomer){
                    this.saleEntityList.push(sale);
                  }
                },
                ()=>{
                  this.loginService.logout();
                  location.reload();
                }
              );
            });
          },
          ()=>location.reload()//if username or token invalid reload page
        );
      }
    });
    
  }

  protected get isCustomer(){
    return AppComponent.isCustomer$;
  }
  protected get isEmployee(){
    return AppComponent.isEmployee$;
  }
  protected get isOwner(){
    return AppComponent.isOwner$;
  }
  protected get isAdministrator(){
    return AppComponent.isAdministrator$;
  }
  /**
   * 
   * @returns return list languages
   */
  protected getIdioms() {
    let listLanguages: { code: string, name: string }[] = [];
    listLanguages.push({ code: "en", name: "English" });
    listLanguages.push({ code: "es", name: "Spanish" });
    return listLanguages;
  }


  /**
   * close session
   */
  protected logOut() {
    this.alertService.alertSuccess("Successful", "access authority", false, 1500, true);
    this.loginService.logout();
    this.isLoggedIn$ = of(false);
  }

  /**
   * this method load data in shopping cart
   * @param code this id of ServiceTourismEntity
   */
  public static addCodeServiceList(code: string, serviceTourismService:ServiceTourismService) {
    serviceTourismService.getEntity(code).subscribe(data => {
      const exists = AppComponent.saleServiceEntityList.some(s => s.codeService === data.codeService);
      if (!exists) {
        AppComponent.saleServiceEntityList.push(data);
        sessionStorage.setItem("saleServiceEntityList", JSON.stringify(AppComponent.saleServiceEntityList));
      }
    });
  }

  /**
   * this method load data in shopping cart
   * @param code this id of ServiceTourismEntity
   */
  public static addCodePackageList(code: string, packageTourismService: PackageTourismService) {
    packageTourismService.getEntity(code).subscribe(data => {
      const exists = this.salePackageEntityList.some(s => s.codePackage === data.codePackage);
      if (!exists) {
        this.salePackageEntityList.push(data);
        sessionStorage.setItem("salePackageEntityList", JSON.stringify(this.salePackageEntityList));
      }
    });
  }

  /**
   * this method remove data in shopping cart
   * @param code this id of serviceTorismEntoty
   */
  protected removeSaleServiceTourism(code: string) {
    const index = AppComponent.saleServiceEntityList.findIndex(service => service.codeService === code);

    if (index !== -1) {
      AppComponent.saleServiceEntityList.splice(index, 1);
      this.alertService.alertInfo("service removed from shopping cart", 1300, "red");
    } else {
      console.error(`El código de servicio ${code} no existe y no se ha eliminado.`);
    }
    sessionStorage.setItem("saleServiceEntityList", JSON.stringify(AppComponent.saleServiceEntityList));
  }

  /**
   * this method remove data in shopping cart
   * @param code this id of serviceTorismEntoty
   */
  protected removeSalePackageTourism(code: string) {
    const index = AppComponent.salePackageEntityList.findIndex(service => service.codePackage === code);

    if (index !== -1) {
      AppComponent.salePackageEntityList.splice(index, 1);
      this.alertService.alertInfo("package removed from shopping cart", 1300, "red");
    } else {
      console.error(`El código de servicio ${code} no existe y no se ha eliminado.`);
    }
    sessionStorage.setItem("salePackageEntityList", JSON.stringify(AppComponent.salePackageEntityList));
  }

  /**
   * 
   * @returns return all cost in ServicesService
   */
  protected getCostServiceTourism(): number {
    let cost = 0;
    for (let s of AppComponent.saleServiceEntityList) {
      cost += s.costService;
    }
    return cost;
  }

  /**
   * 
   * @returns return all cost in PackageEntity
   */
  protected getCostPackageTourism(): number {
    let cost = 0;
    for (let s of this.salePackageEntityList) {
      cost += s.cost;
    }
    return cost;
  }

  /**
   * this method buy services and packages;
   */
  protected buyPackageServiceTouris(): void {
    let codeServicesList: string[] = [];
    let codePackagesList: string[] = [];
    
    AppComponent.saleServiceEntityList.forEach(t => codeServicesList.push(t.codeService));
    AppComponent.salePackageEntityList.forEach(t => codePackagesList.push(t.codePackage));
    
    if (codePackagesList.length > 0 || codeServicesList.length > 0) {
      
      this.isLoggedIn$.subscribe(data=>{
        if(data){
          AppComponent.isCustomer$.subscribe(data=>{
            if(data){//successful sale
              this.customerService.findIdByUsername(this.loginService.Username, this.loginService.Token).subscribe(
                idCustomer=>{
                  let saleEntity: SaleEntity = new SaleEntity(0, "2024-04-07", "Card", idCustomer, 1, codePackagesList, codeServicesList);
                  sessionStorage.removeItem("salePackageEntityList");
                  sessionStorage.removeItem("saleServiceEntityList");
                  this.saleService.cretate(saleEntity, this.loginService.Token);
                  this.alertService.alertSuccess("Shopping", "Shopping Successful", true, 1500, true);
                },
                error=>{
                  console.error(error);
                  this.alertService.alertError("shopping fail", "Error in shopping, please try again", "");
                }
              );
            }else{
              console.error("not is customer");
              this.alertService.alertError("doesn´t customer", "you must be a client", "");
            }
          });
        }else{
          console.error("not login");
          this.alertService.alertError("Login Error", "Doenst not login", "");
        }
      });
      
      /*if (this.isLoggedIn$) {
        let saleEntity: SaleEntity = new SaleEntity(0, "2024-04-07", "Card", 1, 1, codePackagesList, codeServicesList);
        this.saleService.cretate(saleEntity, this.loginService.Token);
        this.alertService.alertSuccess("Shopping", "Shopping Successful", true, 1500, true);
      } else {
        console.error("not login");
        this.alertService.alertError("Login Error", "Doenst not login", "");
      }*/
    } else {
      this.alertService.alertError("Shopping Error", "must have at least one product or service", "");
    }
  }

  public get getSaleServiceEntityList() : ServiceTourismEntity[]{
    return AppComponent.saleServiceEntityList;
  }
  
  public get salePackageEntityList() : PackageTourismEntity[] {
    return AppComponent.salePackageEntityList;
  }

  /**
 * Check if a specific role is in login
 * @param role - The role to check
 * @returns Whether the role is in login
 */
  private hasRole(role: string): Observable<boolean> {
    try {
      return of(this.loginService.Role.includes(role));
    } catch (error) {
      return of(false);
    }
  }
  
  protected loadDataInSaleShopping(code: number): void {
    let aux: any = null;
    for (let i of this.saleEntityList) {
      if (code == i.numberSale) {
        aux = i;
        break;
      }
    }
    if (aux != null) {
      this.saleEntity=aux;
    } else {
      console.error("this code not exist");
    }
  }

}