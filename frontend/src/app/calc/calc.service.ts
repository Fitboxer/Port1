import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmisionesDTO } from './emisionesDTO';

@Injectable({
  providedIn: 'root'
})
export class CalcService {
  private apiUrl = 'http://localhost:8080/enagas';

  constructor(private http: HttpClient) { }

  postDatos(data: EmisionesDTO): Observable<number> {
    return this.http.post<number>(`${this.apiUrl}/volumen`, data);
  }

  postEnergia(data: EmisionesDTO): Observable<number> {
    return this.http.post<number>(`${this.apiUrl}/energia`, data);
  }

}
