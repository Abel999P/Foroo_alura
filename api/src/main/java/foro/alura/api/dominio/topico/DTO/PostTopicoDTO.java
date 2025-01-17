package foro.alura.api.dominio.topico.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostTopicoDTO(
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotNull Long usuarioId,
    @NotNull Long cursoId
) {
    
}
