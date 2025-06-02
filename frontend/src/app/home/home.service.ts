import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private apiUrl = 'http://localhost:8080/enagas';

  constructor(private http: HttpClient) {}

  login(name: string, password: string): Observable<string> {
    const user = { name, password };
    return this.http.post(`${this.apiUrl}/login`, user, { responseType: 'text' });
  }

  guardarToken(token: string): void {
    localStorage.setItem('token', token);
  }

  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  estaAutenticado(): boolean {
    return !!this.obtenerToken();
  }
}
