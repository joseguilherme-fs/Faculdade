import { Injectable } from '@angular/core';
import { Livro } from '../models/livro.model';

@Injectable({
  providedIn: 'root'
})
export class LivroService {
  private livros: Livro[] = [];
  private idCounter = 1;

  constructor() { }

  getLivros(): Livro[] {
    return this.livros;
  }

  addLivro(livro: Livro): void {
    livro.id = this.idCounter++;
    this.livros.push(livro);
  }

  updateLivro(livro: Livro): void {
    const index = this.livros.findIndex(l => l.id === livro.id);
    if (index !== -1) {
      this.livros[index] = livro;
    }
  }

  deleteLivro(id: number): void {
    this.livros = this.livros.filter(l => l.id !== id);
  }
}
