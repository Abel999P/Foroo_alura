package foro.alura.api.dominio.curso.DTO;

import foro.alura.api.dominio.curso.Categoria;
import foro.alura.api.dominio.curso.Curso;

public record GetCursoDTO(
    Long id,
    String name,
    Categoria categoria,
    Boolean activo) {

public GetCursoDTO(Curso curso){
    this(
            curso.getId(),
            curso.getNombre(),
            curso.getCategoria(),
            curso.getActivo());
}

}