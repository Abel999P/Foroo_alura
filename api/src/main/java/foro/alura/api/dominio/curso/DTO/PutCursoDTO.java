package foro.alura.api.dominio.curso.DTO;

import foro.alura.api.dominio.curso.Categoria;

public record PutCursoDTO(
        String name,
        Categoria categoria,
        Boolean activo) {
}