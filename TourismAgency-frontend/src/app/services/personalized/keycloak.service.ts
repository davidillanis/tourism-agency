import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of } from 'rxjs';
import { UserPersonDTO } from 'src/app/util/dto/user-person-dto';
import { ConfigServerProvider } from 'src/app/util/oter/config-server-provider';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  private baseApiURL = ConfigServerProvider.concatBackendAPI("/keycloak/user");

  constructor(private httpClient: HttpClient) { }

  isValidToken(token: string): Observable<boolean> {
    const apiURL = `${this.baseApiURL}/validateToken`;

    return this.httpClient.post<any>(apiURL, token).pipe(
      map(data => {
        return data['ValidToken'];
      }),
      catchError(error => {
        console.error(error);
        return of(false); // Devuelve un Observable con valor false en caso de error
      })
    );
  }

  cretateCustomer(entity:UserPersonDTO): void {
    let apiURL = this.baseApiURL + "/create-customer";
    this.httpClient.post(`${apiURL}`, entity).subscribe();
  }

  existUsername(username:string): Observable<any>{
    let apiURL=this.baseApiURL+"/exist-username/"+username;
    return this.httpClient.get<any[]>(`${apiURL}`);
  }

  getRoleByToken(token:string){
    let apiURL=this.baseApiURL+"/role";
    return this.httpClient.post<any>(`${apiURL}`, token);
  }
  /*getListEntity(token:string): Observable<any> {
    let apiURL = this.baseApiURL + "/list";
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.httpClient.get<any[]>(`${apiURL}`, {headers:header});
  }

  getEntity(id: string, token:string): Observable<any> {
    let apiURL = this.baseApiURL + "/entity/" + id;
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.httpClient.get<any>(`${apiURL}`, {headers:header});
  }

  updateEntity(entity: any, token: string): void {
    let apiURL = this.baseApiURL + "/update";
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    this.httpClient.put(`${apiURL}`, entity, { headers: header }).subscribe();
  }

  deleteEntity(id: string, token: string): void {
    let apiURL = this.baseApiURL + "/delete/" + id;
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    this.httpClient.delete(`${apiURL}`, { headers: header }).subscribe();
  }*/
}
