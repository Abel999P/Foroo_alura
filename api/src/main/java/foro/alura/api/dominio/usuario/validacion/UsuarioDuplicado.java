package foro.alura.api.dominio.usuario.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foro.alura.api.dominio.usuario.UsuarioRepository;
import foro.alura.api.dominio.usuario.DTO.PostUsuarioDTO;
import jakarta.validation.ValidationException;

@Component
public class UsuarioDuplicado implements ValidarCrearUsuario{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(PostUsuarioDTO data) {
        var usuarioDuplicado = repository.findByUsername(data.username());
        if(usuarioDuplicado != null){
            throw new ValidationException("Este usuario ya existe.");
        }

        var emailDuplicado = repository.findByEmail(data.email());
        if(emailDuplicado != null){
            throw new ValidationException("Este email ya existe.");
        }
    }
}