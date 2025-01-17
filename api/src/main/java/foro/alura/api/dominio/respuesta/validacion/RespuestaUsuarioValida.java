package foro.alura.api.dominio.respuesta.validacion;

import org.springframework.beans.factory.annotation.Autowired;

import foro.alura.api.dominio.respuesta.DTO.PostRespuestaDTO;
import foro.alura.api.dominio.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;

public class RespuestaUsuarioValida implements ValidarRespuestaCreada{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validate(PostRespuestaDTO data) {
        // TODO Auto-generated method stub
        var usuarioExiste = usuarioRepository.existsById(data.usuarioId());

        if(!usuarioExiste){
            throw new ValidationException("Estado usuario no existe.");
        }

        var usuarioHabilitado = usuarioRepository.findById(data.usuarioId()).get().isEnabled();
        
        if(!usuarioHabilitado){
            throw new ValidationException("Este usurio no esta habilitado");
        }
    }
   
}