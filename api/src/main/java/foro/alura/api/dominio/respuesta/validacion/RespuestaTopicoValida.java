package foro.alura.api.dominio.respuesta.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foro.alura.api.dominio.respuesta.DTO.PostRespuestaDTO;
import foro.alura.api.dominio.topico.Estado;
import foro.alura.api.dominio.topico.TopicoRepository;
import jakarta.validation.ValidationException;

@Component
public class RespuestaTopicoValida implements ValidarRespuestaCreada{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validate(PostRespuestaDTO data) {
        // TODO Auto-generated method stub

        var topicoExiste = topicoRepository.existsById(data.topicoId());

        if(!topicoExiste){
            throw new ValidationException("Este topico no existe.");
        }

        var topicoAbierto = topicoRepository.findById(data.topicoId()).get().getEstado();
        if(topicoAbierto != Estado.OPEN){
            throw new ValidationException("Este topico no esta abierto.");
        }
    }

}