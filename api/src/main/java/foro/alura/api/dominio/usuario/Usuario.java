package foro.alura.api.dominio.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import foro.alura.api.dominio.usuario.DTO.PostUsuarioDTO;
import foro.alura.api.dominio.usuario.DTO.PutUsuarioDTO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity(name = "Usuario")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @SuppressWarnings("unused")
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String nombre;
    private String apellido;
    private String email;
    private Boolean enabled;

    public Usuario(PostUsuarioDTO postUsuarioDTO, String password) {
        this.username = postUsuarioDTO.username();
        this.password = password;
        this.rol = Rol.USUARIO;
        this.nombre = postUsuarioDTO.nombre();
        this.apellido = postUsuarioDTO.apellido();
        this.email = postUsuarioDTO.email();
        this.enabled = true;
    }

    public void actualizarUsuarioConPassword(PutUsuarioDTO putUsuarioDTO, String hashedPassword) {
        if (putUsuarioDTO.password() != null) {
            this.password = hashedPassword;
        }
        if (putUsuarioDTO.rol() != null) {
            this.rol = putUsuarioDTO.rol();
        }
        if (putUsuarioDTO.nombre() != null) {
            this.nombre = capitalizado(putUsuarioDTO.nombre());
        }
        if (putUsuarioDTO.apellido() != null) {
            this.apellido = capitalizado(putUsuarioDTO.apellido());
        }
        if (putUsuarioDTO.email() != null) {
            this.email = putUsuarioDTO.email();
        }
        if (putUsuarioDTO.enabled() != null) {
            this.enabled = putUsuarioDTO.enabled();
        }
    }

    public void actualizarUsuario(PutUsuarioDTO putUsuarioDTO) {
        if (putUsuarioDTO.rol() != null) {
            this.rol = putUsuarioDTO.rol();
        }
        if (putUsuarioDTO.nombre() != null) {
            this.nombre = capitalizado(putUsuarioDTO.nombre());
        }
        if (putUsuarioDTO.apellido() != null) {
            this.apellido = capitalizado(putUsuarioDTO.apellido());
        }
        if (putUsuarioDTO.email() != null) {
            this.email = putUsuarioDTO.email();
        }
        if (putUsuarioDTO.enabled() != null) {
            this.enabled = putUsuarioDTO.enabled();
        }
    }

    public void eliminarUsuario() {
        this.enabled = false;
    }

    private String capitalizado(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}