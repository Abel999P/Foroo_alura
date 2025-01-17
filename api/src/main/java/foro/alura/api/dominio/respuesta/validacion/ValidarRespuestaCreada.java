package foro.alura.api.dominio.respuesta.validacion;

import foro.alura.api.dominio.respuesta.DTO.PostRespuestaDTO;

public interface ValidarRespuestaCreada {

    void validate(PostRespuestaDTO data);
}