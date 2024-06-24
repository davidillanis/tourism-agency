import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeEntity } from 'src/app/model/employee-entity';
import { ConfigServerProvider } from 'src/app/util/oter/config-server-provider';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseApiURL = ConfigServerProvider.concatBackendAPI("/api/employee");

  constructor(private httpClient: HttpClient) { }

  cretate(entity: EmployeeEntity, token:string): void {
    let apiURL = this.baseApiURL + "/create";
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    this.httpClient.post(`${apiURL}`, entity, {headers:header}).subscribe();
  }

  getListEntity(token:string): Observable<EmployeeEntity[]> {
    let apiURL = this.baseApiURL + "/list";
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.httpClient.get<EmployeeEntity[]>(`${apiURL}`, {headers:header});
  }

  getEntity(id: string, token:string): Observable<EmployeeEntity> {
    let apiURL = this.baseApiURL + "/entity/" + id;
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    return this.httpClient.get<EmployeeEntity>(`${apiURL}`, {headers:header});
  }

  updateEntity(entity: EmployeeEntity, token: string): void {
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
}
