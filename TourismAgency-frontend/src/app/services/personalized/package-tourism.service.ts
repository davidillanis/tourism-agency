import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PackageTourismEntity } from 'src/app/model/package-tourism-entity';
import { ConfigServerProvider } from 'src/app/util/oter/config-server-provider';
import { AlertService } from '../external/alert.service';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PackageTourismService {
  private baseApiURL = ConfigServerProvider.concatBackendAPI("/api/package");

  constructor(private httpClient: HttpClient, private loginService:LoginService) { }

  cretate(entity: PackageTourismEntity, token:string): void {
    let apiURL = this.baseApiURL + "/create";
    let header = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    this.httpClient.post(`${apiURL}`, entity, {headers:header}).subscribe(
      ()=>console.log(),
      ()=>this.loginService.dinamicLogOut("The session has expired")
    );
  }

  getListEntity(available:string="true"): Observable<PackageTourismEntity[]> {
    let apiURL = this.baseApiURL + "/list/"+available;
    return this.httpClient.get<PackageTourismEntity[]>(`${apiURL}`);
  }

  getEntity(id: string): Observable<PackageTourismEntity> {
    let apiURL = this.baseApiURL + "/entity/" + id;
    return this.httpClient.get<PackageTourismEntity>(`${apiURL}`);
  }

  updateEntity(entity: PackageTourismEntity, token: string): void {
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
