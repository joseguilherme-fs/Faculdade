import { Injectable } from '@angular/core';
import {Usuario} from "../model/usuario";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsuarioRestService {

  URL_USUARIOS = 'http://localhost:3000/usuarios/';
  constructor(private httpClient: HttpClient) { }

  listar(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.URL_USUARIOS);
  }

  BuscarPorId(id: String): Observable<Usuario> {
    return this.httpClient.get<Usuario>(this.URL_USUARIOS + id);
  }

  inserir(usuario: Usuario): Observable<Usuario> {
    return this.httpClient.post<Usuario>(this.URL_USUARIOS, usuario);
  }

  remover(id: String): Observable<Usuario> {
    return this.httpClient.delete<Usuario>(this.URL_USUARIOS + id);
  }

  atualizar(usuario: Usuario): Observable<Usuario> {
    return this.httpClient.put<Usuario>(this.URL_USUARIOS + usuario.id, usuario);
  }
}
