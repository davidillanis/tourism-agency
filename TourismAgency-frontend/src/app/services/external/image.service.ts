import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ImageEntity } from 'src/app/model/image-entity';
import { ConfigServerProvider } from 'src/app/util/oter/config-server-provider';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private httpClient:HttpClient) { }

  upload(entity:ImageEntity){
    let apiURL = "https://api.imgbb.com/1/upload";
    const headers = new HttpHeaders({
      'Content-Type': 'multipart/form-data',
    });
    this.httpClient.post<any>(`${apiURL}`, entity, {headers:headers}).subscribe();
  }
  

}
