import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  userName: string = '';
  userRole: string = '';

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.userName$.subscribe(name => {
      this.userName = name;
    });

    this.authService.userRole$.subscribe(role => {
      this.userRole = role;
    });

    const token = localStorage.getItem('token');
    if (token) {
      const payload = token.split('.')[1];
      const decodedPayload = atob(payload);
      const payloadObj = JSON.parse(decodedPayload);
      console.log('Payload object:', payloadObj);
      this.authService.setUserName(payloadObj.name || payloadObj.sub || '');
      this.authService.setUserRole(payloadObj.role || '');
    }
  }
}