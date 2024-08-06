import { Component } from '@angular/core';
import { Livro } from './shared/models/livro.model';
import { LivroService } from './shared/services/livro.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  livros: Livro[];
  livro: Livro = new Livro(0, '', '', 0);
  editMode: boolean = false;

  constructor(private livroService: LivroService) {
    this.livros = this.livroService.getLivros();
  }

  addLivro(): void {
    this.livroService.addLivro(this.livro);
    this.livro = new Livro(0, '', '', 0);
    this.livros = this.livroService.getLivros();
  }

  editLivro(livro: Livro): void {
    this.livro = { ...livro };
    this.editMode = true;
  }

  updateLivro(): void {
    this.livroService.updateLivro(this.livro);
    this.livro = new Livro(0, '', '', 0);
    this.editMode = false;
    this.livros = this.livroService.getLivros();
  }

  deleteLivro(id: number): void {
    this.livroService.deleteLivro(id);
    this.livros = this.livroService.getLivros();
  }
}
