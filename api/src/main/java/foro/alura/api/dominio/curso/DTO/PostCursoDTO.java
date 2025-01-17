package foro.alura.api.dominio.curso.DTO;

import foro.alura.api.dominio.curso.Categoria;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;

public record PostCursoDTO(
    @NotBlank String nombre,
    @NonNull Categoria categoria
) {
} 