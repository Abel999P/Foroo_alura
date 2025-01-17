package foro.alura.api.dominio.topico.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foro.alura.api.dominio.topico.TopicoRepository;
import foro.alura.api.dominio.topico.DTO.PostTopicoDTO;
import jakarta.validation.ValidationException;

@Component
public class TopicoDuplicado implements ValidarTopicoCreado{
    
    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validate(PostTopicoDTO data) {
        // TODO Auto-generated method stub
        var topicoDuplicdo = topicoRepository.existsByTituloAndMensaje(data.titulo(), data.mensaje());
        if(topicoDuplicdo){
            throw new ValidationException("Este topico ya existe. Revisa /topicos/" + topicoRepository.findByTitulo(data.titulo()).getId());        
        }
    }
        
}