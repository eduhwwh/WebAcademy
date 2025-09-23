import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-seletor-tamanho-pagina',
  imports: [],
  templateUrl: './seletor-tamanho-pagina.component.html',
  styles: ``
})
export class SeletorTamanhoPaginaComponent {
  @Input() tamanhoAtual: number = 10;
  @Input() opcoes: number[] = [5, 10, 20, 50];
  @Output() mudancaTamanho = new EventEmitter<number>();

  alterarTamanho(event: Event): void {
    const target = event.target as HTMLSelectElement;
    this.mudancaTamanho.emit(+target.value);
  }
}