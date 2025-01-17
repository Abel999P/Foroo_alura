package foro.alura.api.dominio.usuario.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PostUsuarioDTO(
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String nombre,
    @NotBlank String apellido,
    @NotBlank @Email String email
) {
}