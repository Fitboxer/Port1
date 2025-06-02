import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExcelDTO } from './excelDTO.model';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-excel',
  templateUrl: './excel.component.html',
  styleUrls: ['./excel.component.css']
})
export class ExcelComponent {
  selectedFile: File = null;
  excelData: ExcelDTO[] = [];
  isLoading = false;

  displayedColumns: string[] = [
    'fecha_movimiento',
    'infraestructura',
    'observaciones',
    'descripcion',
    'valor',
    'justificante',
    'observacionesAEAT'
  ];
  dataSource = new MatTableDataSource<ExcelDTO>();

  constructor(private http: HttpClient) { }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  onUpload(): void {
    if (!this.selectedFile) {
      alert('Por favor selecciona un archivo primero.');
      return;
    }

    const formData = new FormData();
    formData.append('archivo', this.selectedFile);

    this.isLoading =true;

    this.http.post<ExcelDTO[]>('http://localhost:8080/enagas/leerExcel', formData)
      .subscribe({
        next: (response) => {
          this.excelData = response;
          this.dataSource.data = this.excelData;
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Error al subir archivo:', err);
          alert('Error al subir archivo');
          this.isLoading = false;
        }
      });
  }

  limpiarTabla() {
    this.excelData = [];
    console.log('Tabla limpiada:', this.excelData);
  }
}
