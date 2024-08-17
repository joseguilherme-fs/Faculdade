import {Injectable} from '@angular/core';
import {Usuario} from "../model/usuario";
import { UsuarioRestService } from './usuario-rest.service';

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {

    // usuarios = USUARIOS;

    constructor(private usuarioRestService: UsuarioRestService) {
    }

    inserir(usuario: Usuario) {
        this.validarMaiorIdade(usuario);
        this.validarIdsDiferentes(usuario);

        this.usuarioRestService.inserir(usuario).subscribe(
            {
                next: () => {
                  return "Usuário cadastrado com sucesso.";
                },
                error: (e) => {
                  return e.message;
                }
              });
    }

    atualizar(usuario: Usuario) {
        this.usuarioRestService.atualizar(usuario).subscribe(
            {
              next: () => {
              return "Usuário alterado com sucesso."
              },
              error: (e) => {
                return e.message;
              }
            });
    }

    listar() {
        this.usuarioRestService.listar().subscribe(
            {
              next: (usuariosRetornados) => {
                return usuariosRetornados;
              }
            });
    }

    remover(id: string) {
        this.usuarioRestService.remover(id).subscribe(
            {
                next: () => {
                    return "Removido com sucesso"
                }
            }
        )
    }

    private validarMaiorIdade(usuario: Usuario) {
        if (usuario.idade < 18) {
            throw new Error('Usuário nao pode ser menor!');
        }
    }

    private validarIdsDiferentes(usuario: Usuario) {
        const usuarioEncontrado = this.usuarioRestService.BuscarPorId(usuario.id).subscribe(

        );
        if (usuarioEncontrado) {
            throw new Error('Usuário já cadastrado com ID!');
        }
    }
}
