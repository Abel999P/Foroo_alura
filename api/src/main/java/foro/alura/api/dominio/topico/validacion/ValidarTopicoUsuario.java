package foro.alura.api.dominio.topico.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foro.alura.api.dominio.topico.DTO.PostTopicoDTO;
import foro.alura.api.dominio.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;

@Component
public class ValidarTopicoUsuario implements ValidarTopicoCreado{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validate(PostTopicoDTO data) {
        // TODO Auto-generated method stub
        var existeUsuario = usuarioRepository.existsById(data.usuarioId());
        if(!existeUsuario){
            throw new ValidationException("Este usuario no existe");
        }

        var usuarioHabilitado = usuarioRepository.findById(data.usuarioId()).get().getEnabled();
        if(!usuarioHabilitado){
            throw new ValidationException("Este usuario fue desabilitado.");
        }
    }

}