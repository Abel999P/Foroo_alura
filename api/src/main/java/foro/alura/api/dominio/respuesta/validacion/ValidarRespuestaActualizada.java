package foro.alura.api.dominio.respuesta.validacion;

import foro.alura.api.dominio.respuesta.DTO.PutRespuestaDTO;

public interface ValidarRespuestaActualizada {

    void validate(PutRespuestaDTO data, Long respuestaId);
}
