import { Component } from '@angular/core';
import { CalcService } from './calc.service';
import { EmisionesDTO } from './emisionesDTO';

@Component({
  selector: 'app-calc',
  templateUrl: './calc.component.html',
  styleUrls: ['./calc.component.css']
})
export class CalcComponent {

  datos: EmisionesDTO = {
    emisiones: null,
    factorEmision: null,
    pcs: null
  };

  resultadoVolumen: number | null = null;
  resultadoEnergia: number | null = null;

  constructor(private calcService: CalcService) {}

  calcularVolumen() {
    this.calcService.postDatos(this.datos).subscribe({
      next: res => this.resultadoVolumen = res,
      error: err => console.error('Error al calcular volumen:', err)
    });
  }

  calcularEnergia() {
    this.calcService.postEnergia(this.datos).subscribe({
      next: res => this.resultadoEnergia = res,
      error: err => console.error('Error al calcular energía:', err)
    });
  }

  onKeyDown(event: KeyboardEvent) {
    if (event.key === "ArrowUp" || event.key === "ArrowDown") {
      event.preventDefault();
    }
  }

}
