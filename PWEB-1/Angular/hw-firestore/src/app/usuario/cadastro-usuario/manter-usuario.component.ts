import { Component } from '@angular/core';
import {Usuario} from "../../shared/model/usuario";
import {ActivatedRoute, Router} from "@angular/router";
import {UsuarioService} from "../../shared/services/usuario.service";
import Swal from "sweetalert2";
import {MensagemSweetService} from "../../shared/services/mensagem-sweet.service";
import {UsuarioRestService} from "../../shared/services/usuario-rest.service";


@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './manter-usuario.component.html',
  styleUrl: './manter-usuario.component.scss'
})
export class ManterUsuarioComponent {

  usuario = new Usuario('1', '', 0);
  modoCadastro = true;

  constructor(private roteador: Router, private rotaAtual: ActivatedRoute,
              private usuarioRestService: UsuarioRestService, private mensagemService: MensagemSweetService) {
    const idParaEdicao = this.rotaAtual.snapshot.paramMap.get('id');
    if (idParaEdicao) {
      this.modoCadastro = false;
      const usuarioAEditar = usuarioRestService.BuscarPorId(idParaEdicao).subscribe(
        {
          next: usuarioDevolvido => this.usuario = usuarioDevolvido
        }

      );
      
    }
  }

  inserir() {
    if (this.rotaAtual.snapshot.paramMap.get('id')) {
      this.usuarioRestService.atualizar(this.usuario).subscribe(
        {
          next: () => {
          this.mensagemService.sucesso("Alterado com sucesso.")
          this.roteador.navigate(['listagem-usuarios']);
          }
        });

    } else {
        this.usuarioRestService.inserir(this.usuario).subscribe(
          {
            next: () => {
              this.roteador.navigate(['listagem-usuarios']);
              this.mensagemService.sucesso('Usuário cadastrado com sucesso.');
            },
            error: (e) => {
              this.mensagemService.erro(e.message);
            }
          });
    }
  }
}
