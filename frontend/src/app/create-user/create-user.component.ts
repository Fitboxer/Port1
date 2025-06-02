import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService, UserDTO } from './user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  userForm: FormGroup;
  roles: string[] = ['ADMIN', 'USER'];
  mensajeExito: string = '';
  mensajeError: string = ''; 

  constructor(private fb: FormBuilder, private userService: UserService) {}

  ngOnInit() {
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.userForm.valid) {
      const newUser: UserDTO = this.userForm.value;
      this.userService.createUser(newUser).subscribe({
        next: (response) => {
          console.log('Respuesta:', response);
          if (response === 'User created') {
            this.mensajeExito = 'Usuario creado correctamente.';
            this.mensajeError = '';
            this.userForm.reset();
          } else if (response === 'Ya existe un usuario con ese nombre') {
            this.mensajeError = 'Ya existe un usuario con ese nombre.';
            this.mensajeExito = '';
          } else {
            this.mensajeError = 'Respuesta inesperada: ' + response;
            this.mensajeExito = '';
          }
        },
        error: (error) => {
          console.error('Error:', error);
          this.mensajeError = 'Error al crear usuario.';
          this.mensajeExito = '';
        }
      });
    }
  }
}

