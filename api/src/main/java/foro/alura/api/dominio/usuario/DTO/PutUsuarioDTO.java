package foro.alura.api.dominio.usuario.DTO;

import foro.alura.api.dominio.usuario.Rol;

public record PutUsuarioDTO(
    String password,
    Rol rol,
    String nombre,
    String apellido,
    String email,
    Boolean enabled
) {
}