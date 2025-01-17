package foro.alura.api.dominio.respuesta.DTO;

import java.time.LocalDateTime;

import foro.alura.api.dominio.respuesta.Respuesta;

public record GetRespuestaDTO(
    Long id,
    String mensaje,
    LocalDateTime fechaCreacion,
    LocalDateTime ultimaActualizacion,
    Boolean solucion,
    Boolean borrado,
    Long usuarioId,
    String username,
    Long topicoId,
    String topico
) {

public GetRespuestaDTO(Respuesta respuesta){
    this(
            respuesta.getId(),
            respuesta.getMensaje(),
            respuesta.getFechaDeCreacion(),
            respuesta.getUltimaActualizacion(),
            respuesta.getSolucion(),
            respuesta.getBorrado(),
            respuesta.getUsuario().getId(),
            respuesta.getUsuario().getUsername(),
            respuesta.getTopico().getId(),
            respuesta.getTopico().getTitulo());
}
}