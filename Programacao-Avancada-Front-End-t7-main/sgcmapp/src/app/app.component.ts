import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NotificacaoComponent } from './component/notificacao/notificacao.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, NotificacaoComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'SGCM';
}
