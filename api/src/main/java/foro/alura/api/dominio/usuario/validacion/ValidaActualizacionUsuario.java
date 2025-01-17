package foro.alura.api.dominio.usuario.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foro.alura.api.dominio.usuario.UsuarioRepository;
import foro.alura.api.dominio.usuario.DTO.PutUsuarioDTO;
import jakarta.validation.ValidationException;

@Component
public class ValidaActualizacionUsuario implements ValidarActualizarUsuario {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validate(PutUsuarioDTO data) {
        if(data.email() != null){
            var emailDuplicado = repository.findByEmail(data.email());
            if(emailDuplicado != null){
                throw new ValidationException("Este email ya esta en uso");
            }
        }
    }

}