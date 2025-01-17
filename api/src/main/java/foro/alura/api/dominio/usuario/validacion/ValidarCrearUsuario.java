package foro.alura.api.dominio.usuario.validacion;

import foro.alura.api.dominio.usuario.DTO.PostUsuarioDTO;

public interface ValidarCrearUsuario {
    void validate(PostUsuarioDTO data);    
}