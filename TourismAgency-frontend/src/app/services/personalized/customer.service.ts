import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { CustomerEntity } from 'src/app/model/customer-entity';
import { ConfigServerProvider } from 'src/app/util/oter/config-server-provider';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseApiURL = ConfigServerProvider.concatBackendAPI("/api/customer");

  constructor(private httpClient: HttpClient) { }

  cretate(entity: CustomerEntity): void {
    let apiURL = this.baseApiURL + "/create";
    this.httpClient.post(`${apiURL}`, entity).subscribe();
  }

  getListEntity(token:string): Observable<CustomerEntity[]> {
    let apiURL = this.baseApiURL + "/list";
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.httpClient.get<CustomerEntity[]>(`${apiURL}`, {headers:header});
  }

  getEntity(id: string, token:string): Observable<CustomerEntity> {
    let apiURL = this.baseApiURL + "/entity/" + id;
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.httpClient.get<CustomerEntity>(`${apiURL}`, {headers:header});
  }

  updateEntity(entity: CustomerEntity, token: string): void {
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
  }

  //other
  /**
   * search id customer by username
   * @param username if have username this customer
   * @param token this token authorization
   * @returns return id of customer, null if not exist username
   */
  findIdByUsername(username: string, token:string): Observable<number>{
    //this.customerService.findIdByUsername(username, this.loginService.getToken()).subscribe(data=>console.log(data));
    let apiURL = this.baseApiURL + "/search/username/" + username;
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.httpClient.get<any>(`${apiURL}`, {headers:header}).pipe(
      map(data=>{
        return data['idCustomer']
      })
    );
  }
  
}
