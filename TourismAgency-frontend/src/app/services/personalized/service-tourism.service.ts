import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceTourismEntity } from 'src/app/model/service-tourism-entity';
import { ConfigServerProvider } from 'src/app/util/oter/config-server-provider';

@Injectable({
  providedIn: 'root'
})
export class ServiceTourismService {
  private baseApiURL = ConfigServerProvider.concatBackendAPI("/api/service");

  constructor(private httpClient: HttpClient) { }

  cretate(entity: ServiceTourismEntity, token: string): void {
    let apiURL = this.baseApiURL + "/create";
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    this.httpClient.post(`${apiURL}`, entity, { headers: header }).subscribe();
  }

  getListEntity(available:string="true"): Observable<ServiceTourismEntity[]> {
    let apiURL = this.baseApiURL + "/list/"+available;
    return this.httpClient.get<ServiceTourismEntity[]>(`${apiURL}`);
  }

  getEntity(id: string): Observable<ServiceTourismEntity> {
    let apiURL = this.baseApiURL + "/entity/" + id;
    return this.httpClient.get<ServiceTourismEntity>(`${apiURL}`);
  }

  updateEntity(entity: ServiceTourismEntity, token: string): void {
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
