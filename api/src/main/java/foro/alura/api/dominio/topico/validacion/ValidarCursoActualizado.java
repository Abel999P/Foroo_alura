package foro.alura.api.dominio.topico.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foro.alura.api.dominio.curso.CursoRepository;
import foro.alura.api.dominio.topico.DTO.PutTopicoDTO;
import jakarta.validation.ValidationException;

@Component
public class ValidarCursoActualizado implements ValidarTopicoActualizado{

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validate(PutTopicoDTO data) {
        // TODO Auto-generated method stub
        if(data.cursoId() != null){
            var ExisteCurso = cursoRepository.existsById(data.cursoId());
            if(!ExisteCurso){
                throw new ValidationException("Este curso no existe.");
            }

            var cursoHabilitado = cursoRepository.findById(data.cursoId()).get().getActivo();
            if(!cursoHabilitado){
                throw new ValidationException("Este curso no esta disponible en este momento.");
            }
        }
    }

}