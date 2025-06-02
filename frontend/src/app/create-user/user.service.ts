import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UserDTO {
  name: string;
  password: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/enagas/new';
  
  constructor(private http: HttpClient) { }

  createUser(user: UserDTO): Observable<any> {
  return this.http.post(this.apiUrl, user, { responseType: 'text' });
  }
}
