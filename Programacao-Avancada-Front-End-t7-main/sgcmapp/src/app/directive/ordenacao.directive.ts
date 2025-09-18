import { AfterViewInit, Directive, ElementRef, inject, output } from '@angular/core';

@Directive({
  selector: 'thead[ordenacao]'
})
export class OrdenacaoDirective implements AfterViewInit {
  ordenacaoAtualizada = output<string[]>();
  private ordenacao: string[] = [];
  private el = inject(ElementRef);

  ngAfterViewInit(): void {
    this.el.nativeElement.querySelectorAll('th[sort]').forEach((th: HTMLElement) => {
      th.addEventListener('click', () => {
        const campo = th.getAttribute('sort');
        if (campo) {
          this.ordenar(campo);
        }
      });
    });
  }

  private ordenar(campo: string): void {
    if (this.ordenacao.includes(campo)) {
      const indice = this.ordenacao.indexOf(campo);
      this.ordenacao[indice] = `${campo},desc`;
    } else if (this.ordenacao.includes(`${campo},desc`)) {
      const indice = this.ordenacao.indexOf(`${campo},desc`);
      this.ordenacao.splice(indice, 1);
    } else {
      this.ordenacao.push(campo);
    }

    this.atualizarClasses();
    this.ordenacaoAtualizada.emit(this.ordenacao);
  }

  private atualizarClasses(): void {
    this.el.nativeElement.querySelectorAll('th[sort]').forEach((th: HTMLElement) => {
      const campo = th.getAttribute('sort');
      if (!campo) {
        return;
      }

      th.classList.remove('ativo-asc', 'ativo-desc');
      if (this.ordenacao.includes(campo)) {
        th.classList.add('ativo-asc');
      } else if (this.ordenacao.includes(`${campo},desc`)) {
        th.classList.add('ativo-desc');
      }
    });
  }

}
