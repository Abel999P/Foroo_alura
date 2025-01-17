package foro.alura.api.dominio.topico.DTO;

import java.time.LocalDateTime;

import foro.alura.api.dominio.curso.Categoria;
import foro.alura.api.dominio.topico.Estado;
import foro.alura.api.dominio.topico.Topico;

public record GetTopicoDTO(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaDeCreacion,
    LocalDateTime ultimaActualizacion,
    Estado estado,
    String usuario,
    String curso,
    Categoria categoriaCurso

) {

public GetTopicoDTO(Topico topico) {
    this(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaDeCreacion(),
            topico.getUltimaActualizacion(),
            topico.getEstado(),
            topico.getUsuario().getUsername(),
            topico.getCurso().getNombre(),
            topico.getCurso().getCategoria()
    );
}

}