import { Component, Injectable } from '@angular/core';
//npm install --save-dev @types/bootstrap
import { AbstractControl, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import * as bootstrap from 'bootstrap';
import { AppComponent } from 'src/app/app.component';
import { ServiceTourismEntity } from 'src/app/model/service-tourism-entity';
import { UserPersonDTO } from 'src/app/util/dto/user-person-dto';
import { KeycloakService } from '../../personalized/keycloak.service';
import { LoginService } from '../../personalized/login.service';
import { ServiceTourismService } from '../../personalized/service-tourism.service';
import { AlertService } from '../alert.service';

@Component({
  selector: 'app-modal-service',
  templateUrl: './modal-service.component.html',
  styleUrls: ['./modal-service.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class ModalServiceComponent{
  private readonly modalServiceTourism:ModalServiceTourism;
  private readonly modalLogin:ModalLogin;
  
  constructor(private serviceTourismService:ServiceTourismService, 
      private alertService:AlertService,
      private loginService:LoginService,
      private keycloakService:KeycloakService){
    this.modalServiceTourism=new ModalServiceTourism(this.serviceTourismService, this.alertService);
    this.modalLogin=new ModalLogin(this.loginService, this.alertService, this.keycloakService);
  }
  
  
  public get getModalServiceTourism() : ModalServiceTourism {
    return this.modalServiceTourism;
  }

  
  public get getModalLogin() : ModalLogin {
    return this.modalLogin;
  }
  
}

/**this class modal service, use one method's :
 * <button (click)="viewModal()">click</button>
*/
class ModalServiceTourism{
  protected static serviceTourismEntity:ServiceTourismEntity=new ServiceTourismEntity();
  private serviceTourismService:ServiceTourismService;
  private alertService:AlertService;

  constructor(serviceTourismService:ServiceTourismService, alertService:AlertService){
    this.serviceTourismService=serviceTourismService;
    this.alertService=alertService;
  }

  /**
   * view modal service entity info
   * @param codeService this code service tourism entity
   */
  public viewModal(codeService:string){
    const modalElement = document.getElementById('viewModalServicioInfo');
    this.loadDataServiceTourismEntity(codeService);
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    } else {
      console.error("No se encontró ningún elemento con el ID 'modal1'");
    }
  }

  /**
   * method where modal info
   * @param code this id of the ServiceTourismEntity
   */
  public loadDataServiceTourismEntity(code:string):void{
    this.serviceTourismService.getEntity(code).subscribe(data=>{
      ModalServiceTourism.serviceTourismEntity=data;
    });
  }

  /**
   * method where car store
   * @param id code ServiceTourismEntity
   */
  public addSaleServiceTourism(id:string):void{
    AppComponent.addCodeServiceList(id, this.serviceTourismService);
    this.alertService.alertInfo("adding product to shopping cart");
  }

  public get getEntity(){
    return ModalServiceTourism.serviceTourismEntity;
  }
}

/**
 * this class modal login, use two method's
 * <button (click)="viewModal()">click</button>
 * <button aria-current="page" data-bs-toggle="modal" data-bs-target="#log-in-user" data-bs-whatever="@mdo">Login</button>
 */
class ModalLogin{
  
  private loginService:LoginService;
  private alertService:AlertService;
  private keycloakService:KeycloakService;

  public loginForm = new FormGroup({
    username: new FormControl('all', [Validators.required]),
    password: new FormControl('all1234', [Validators.required])
  });
  public registerForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    firstName: new FormControl(''),
    lastName: new FormControl('')
  });
  protected username: string;
  protected isLoggedIn: boolean;

  constructor(loginService:LoginService, alertService:AlertService, keycloakService:KeycloakService){
    this.loginService=loginService;
    this.alertService=alertService;
    this.keycloakService=keycloakService;
    this.registerForm.setValidators(this.passwordMatchValidator());
  }

  /**
   * view modal service entity info
   * @param codeService this code service tourism entity
   */
  public viewModal(){
    const modalElement = document.getElementById('log-in-user');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    } else {
      console.error("No se encontró ningún elemento con el ID 'modal1'");
    }
  }

  /**
   * this method acction a login
   */
  public async formLoginSubmit()/*: Promise<void>*/ {
    let username = this.loginForm.value.username + "";
    let password = this.loginForm.value.password + "";

    //send credentials in service
    this.loginService.sendCredentias(username, password);
    await this.loginService.sendPromise().then(() => {
      //if successful in login
      this.username = username;
      this.alertService.alertSuccess("Successful", "access authority", false, 1500, true);
      this.loginService.addDataInSession();
      this.isLoggedIn = true;
    }).catch(error => {
      //if doesnt successful in login
      this.alertService.alertError("Error", "this username or password incorrect" + error);
    });
  }

   /**
   * this method acction a register
   */
   public register(): void {
    if (this.registerForm.value.password == this.registerForm.value.confirmPassword) {
      this.keycloakService.existUsername(this.registerForm.value.username + "").subscribe(data => {
        if (!data["existUsername"]) {
          let userCustomerDTO: UserPersonDTO = new UserPersonDTO();
          userCustomerDTO.idCustomer = 0;
          userCustomerDTO.firstName = this.registerForm.value.firstName + "";
          userCustomerDTO.lastName = this.registerForm.value.lastName + "";
          userCustomerDTO.address = null;
          userCustomerDTO.dni = null;
          userCustomerDTO.birthdate = null;
          userCustomerDTO.nationality = null;
          userCustomerDTO.cellPhone = null;
          userCustomerDTO.email = this.registerForm.value.email + "";
          userCustomerDTO.usernameKeyCloak = this.registerForm.value.username + "";
          userCustomerDTO.password = this.registerForm.value.password + "";

          this.keycloakService.cretateCustomer(userCustomerDTO);
          this.alertService.alertSuccess("Success", "acount created successfully", true, 1500, true);
        } else {
          this.alertService.alertError("Error in Username", "The username already exists, try another one")
        }
      })
    }
  }

  /**
   * @returns validation in password and confirm password
   */
  private passwordMatchValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      const password = control.get('password');
      const confirmPassword = control.get('confirmPassword');

      if (password && confirmPassword && password.value !== confirmPassword.value) {
        confirmPassword.setErrors({ 'passwordMismatch': true });
        return { 'passwordMismatch': true };
      } else {
        return null;
      }
    };
  }

}