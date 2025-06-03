# ENAGAS - Proyecto Full Stack (Angular + Spring Boot)

Este repositorio contiene una aplicación completa que permite gestionar usuarios, autenticarse con JWT y realizar cálculos de volumen y energía a partir de emisiones.

---

## 🗂️ Estructura del proyecto

```
mi-proyecto/
├── frontend/       # Aplicación Angular 7+
├── backend/        # API REST con Spring Boot (Java 8)
├── README.md       # Este archivo
├── .gitignore      # Archivos y carpetas ignoradas por Git
```

---

## 🚀 Tecnologías utilizadas

### Frontend
- Angular 7+
- Angular Material
- RxJS
- JWT (decodificación manual en cliente)
- HTML / CSS

### Backend
- Java 8
- Spring Boot
- Spring Security (JWT)
- Maven
- JPA / Hibernate
- MySQL / H2 (según tu configuración)

---

## 📦 Instalación y ejecución

### 🔧 Requisitos previos

- Node.js y Angular CLI
- Java 8
- Maven
- Git

---

### 🖥️ Frontend (Angular)

```bash
cd frontend
npm install
ng serve
```

La app estará disponible en: [http://localhost:4200](http://localhost:4200)

---

### 🛠️ Backend (Spring Boot - Java 8)

```bash
cd backend
./mvnw spring-boot:run
```

La API estará disponible en: [http://localhost:8080](http://localhost:8080)

---

## 🔐 Funcionalidad JWT

- Los usuarios deben iniciar sesión con nombre y contraseña.
- Al loguearse, el backend devuelve un **token JWT**.
- Este token se guarda en `localStorage` y se usa en llamadas autenticadas.
- El nombre y rol del usuario se extraen desde el payload del token para controlar vistas y permisos en Angular.

---

## 👤 Roles de usuario

- **ADMIN**: Acceso total a todas las rutas y funcionalidades (como crear usuarios o calcular energía/volumen).
- **USER**: Acceso limitado (sin funciones administrativas).

---

## 🔒 Seguridad y control de acceso

- Angular muestra botones y rutas según el `rol` del usuario.
- Los endpoints del backend también validan el JWT.

---

## 📁 Archivos importantes

- `/frontend/src/app/auth.service.ts`: Servicio que gestiona token, nombre y rol.
- `/frontend/src/app/app.component.ts`: Extrae y muestra información del usuario autenticado.
- `/backend/src/main/java/.../controller`: Controladores de usuario, login y cálculos.
- `/backend/src/main/java/.../security`: Configuración de autenticación y generación de JWT.

---

## 📜 Licencia

Este proyecto es de uso educativo. Puedes modificarlo libremente para tus propias necesidades.

---

## ✍️ Autor

**Adolfo Canales Otí**  
GitHub: [https://github.com/tu-usuario](https://github.com/Fitboxer)
