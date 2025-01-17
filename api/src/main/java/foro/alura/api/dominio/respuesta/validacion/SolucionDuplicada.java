package foro.alura.api.dominio.respuesta.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foro.alura.api.dominio.respuesta.Respuesta;
import foro.alura.api.dominio.respuesta.RespuestaRepository;
import foro.alura.api.dominio.respuesta.DTO.PutRespuestaDTO;
import foro.alura.api.dominio.topico.Estado;
import foro.alura.api.dominio.topico.TopicoRepository;
import jakarta.validation.ValidationException;

@Component
public class SolucionDuplicada implements ValidarRespuestaActualizada{

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired 
    private TopicoRepository topicoRepository;

    @Override
    public void validate(PutRespuestaDTO data, Long respuestaId) {
        // TODO Auto-generated method stub
        if(data.solucion()){
            Respuesta respuesta = respuestaRepository.getReferenceById(respuestaId);
            var topicoResuelto = topicoRepository.getReferenceById(respuesta.getTopico().getId());
            if(topicoResuelto.getEstado() == Estado.CLOSE){
                throw new ValidationException("Este topico ya esta solucionado.");
            }
        }
    }


}