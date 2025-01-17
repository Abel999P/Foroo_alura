package foro.alura.api.dominio.usuario.DTO;

import foro.alura.api.dominio.usuario.Rol;
import foro.alura.api.dominio.usuario.Usuario;

public record GetUsuarioDTO(
    Long id,
    String username,
    Rol rol,
    String nombre,
    String apellido,
    String email,
    Boolean enabled
) {

public GetUsuarioDTO(Usuario usuario){
    this(usuario.getId(),
            usuario.getUsername(),
            usuario.getRol(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getEmail(),
            usuario.getEnabled()
    );
}
}