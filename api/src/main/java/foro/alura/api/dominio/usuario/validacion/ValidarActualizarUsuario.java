package foro.alura.api.dominio.usuario.validacion;

import foro.alura.api.dominio.usuario.DTO.PutUsuarioDTO;

public interface ValidarActualizarUsuario {
    void validate(PutUsuarioDTO data);
}