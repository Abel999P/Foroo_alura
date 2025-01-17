package foro.alura.api.dominio.respuesta.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRespuestaDTO(
    @NotBlank String mensaje,
    @NotNull Long usuarioId,
    @NotNull Long topicoId
) {
    
}
