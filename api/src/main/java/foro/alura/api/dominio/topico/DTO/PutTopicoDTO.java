package foro.alura.api.dominio.topico.DTO;

import foro.alura.api.dominio.topico.Estado;

public record PutTopicoDTO(
    String titulo,
    String mensaje,
    Estado estado,
    Long cursoId
){
}