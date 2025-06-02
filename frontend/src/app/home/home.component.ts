import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeService } from './home.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  datos = {
    name: '',
    password: ''
  };
  loginExitoso = false;

  constructor(
    private homeService: HomeService, 
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {}

  logear(): void {
    this.homeService.login(this.datos.name, this.datos.password).subscribe({
      next: token => {
        this.homeService.guardarToken(token);
        console.log('✅ Login exitoso, token guardado.');

        const payload = token.split('.')[1];
        const decodedPayload = atob(payload);
        const payloadObj = JSON.parse(decodedPayload);

        this.authService.setUserName(payloadObj.name || payloadObj.sub || '');
        this.authService.setUserRole(payloadObj.role || '');

        this.loginExitoso = true;
      },
      error: err => {
        this.loginExitoso = false;
        console.error('❌ Error de login:', err);
        alert('Credenciales incorrectas o servidor no disponible');
      }
    });
  }

  onKeyDown(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.logear();
    }
  }

  logout() {
    localStorage.removeItem('token');
    this.authService.setUserName('');
    this.authService.setUserRole('');
    this.router.navigate(['/']);
    this.datos = { name: '', password: '' };
    this.loginExitoso = false;
  }
}

