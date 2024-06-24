import { Injectable } from '@angular/core';
import { ConfigServerProvider } from 'src/app/util/oter/config-server-provider';
import { KeycloakService } from './keycloak.service';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of } from 'rxjs';
import { AlertService } from '../external/alert.service';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private username:string;
  private password:string;

  constructor(private keycloakService:KeycloakService, private httpClient:HttpClient, private alertService:AlertService, private router:Router){}

  /**
   * this method is the firht method in executed
   * @param username in login add username
   * @param password i this is password
   */
  sendCredentias(username:string, password:string){
    this.username=username;
    this.password=password;
  }

  /**
   * second method in executed, after method 'sendCredentias'
   * @returns return a promise of login. Then if true in login, catch if false in login
   */
  async sendPromise() {
    const url = ConfigServerProvider.apiAuthURL;
    const data = new URLSearchParams();
    data.append('client_id', ConfigServerProvider.clientId);
    data.append('grant_type', ConfigServerProvider.grandType);
    data.append('username', this.username);
    data.append('password', this.password);
    data.append('client_secret', ConfigServerProvider.clientSecret);

    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: data
    });
    if (!response.ok) {
      throw new Error('Error en la solicitud: ' + response.status);
    }
    return await response.json();
  }

  /**
   * this method add data in session, thir method. after method 'sendCredentials' and 'sendPromise'
   */
  addDataInSession():void{
    //sessionStorage.getItem
    this.sendPromise()
    .then(data => {
      localStorage.setItem("token", data['access_token']);
      localStorage.setItem("username", this.username);
      this.keycloakService.getRoleByToken(data['access_token']).subscribe(data=>localStorage.setItem("ROLE",JSON.stringify(data['ROLE'])));
      //localStorage.setItem("username", JSON.stringify(this.username));
    })
    .catch(error => console.error('Error en la solicitud:', error));
  }
  
  /**
   * this method close session
   */
  logout():void{
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("ROLE");
    sessionStorage.removeItem("saleServiceEntityList");
  }

  /**
   * 
   * @returns return true if login
   */
  isLoggedIn(): Observable<boolean> {
    const token = localStorage.getItem('token');
    if (!token) {
      return of(false); // Devuelve un Observable con valor false
    }

    return this.keycloakService.isValidToken(token).pipe(
      map(isValid => isValid), // Si isValidToken devuelve un Observable<boolean>, puedes omitir este paso
      catchError(() => of(false)) // En caso de error, devolverá un Observable con valor false
    );
  }

  /**
   * 
   * @returns return this token
   */
  get Token():string{
    return localStorage.getItem("token")+"";
  }

  /**
   * 
   * @returns return this username
   */
  get Username():string{
    return localStorage.getItem("username")+"";
  }

  /**
   * return this roles by token
   */
  get Role():string[]|any{
    //reload ROLE in localStorage
    this.keycloakService.getRoleByToken(this.Token).subscribe(data=>localStorage.setItem("ROLE",JSON.stringify(data['ROLE'])));

    //delete session if token invalid
    if(this.Token!=undefined && this.Token!=null && this.Token!="" &&  this.Token+""!="null"){
      let apiURL=ConfigServerProvider.concatBackendAPI("/keycloak/user/validateToken");
      this.httpClient.post<any>(`${apiURL}`, this.Token).subscribe(
        data=>{
          if(!data['ValidToken']){
            this.logout();
          }
        },
        ()=>this.logout()
      );
    }

    let role;
    const storedData = localStorage.getItem("ROLE");
    if (storedData) {
      role = JSON.parse(storedData) as string[];
    }
    return role;
  }
  
  dinamicLogOut(error: string = "Not authorized") {
    console.error("no login " + error);
    this.alertService.alertInfo(error, 1600, "red")
    this.router.navigate(['/home']);
    this.logout();
  }
}
