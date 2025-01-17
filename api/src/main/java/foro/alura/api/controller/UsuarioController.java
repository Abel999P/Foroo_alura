package foro.alura.api.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import foro.alura.api.dominio.usuario.Usuario;
import foro.alura.api.dominio.usuario.UsuarioRepository;
import foro.alura.api.dominio.usuario.DTO.GetUsuarioDTO;
import foro.alura.api.dominio.usuario.DTO.PostUsuarioDTO;
import foro.alura.api.dominio.usuario.DTO.PutUsuarioDTO;
import foro.alura.api.dominio.usuario.validacion.ValidarActualizarUsuario;
import foro.alura.api.dominio.usuario.validacion.ValidarCrearUsuario;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    List<ValidarCrearUsuario> crearValidador;

    @Autowired
    List<ValidarActualizarUsuario> actualizarValidador;


    @PostMapping
    @Transactional
    public ResponseEntity<GetUsuarioDTO> crearUsuario(@RequestBody @Valid PostUsuarioDTO crearUsuarioDTO,UriComponentsBuilder uriBuilder) {

        crearValidador.forEach(v -> v.validate(crearUsuarioDTO));

        String hashedPassword = passwordEncoder.encode(crearUsuarioDTO.password());
        Usuario usuario = new Usuario(crearUsuarioDTO, hashedPassword);

        repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{username}").buildAndExpand(usuario.getUsername()).toUri();
        return ResponseEntity.created(uri).body(new GetUsuarioDTO(usuario));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GetUsuarioDTO>> leerTodosUsuarios(
            @PageableDefault(size = 5, sort = { "id" }) Pageable pageable) {
        var pagina = repository.findAll(pageable).map(GetUsuarioDTO::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping
    public ResponseEntity<Page<GetUsuarioDTO>> leerUsuariosActivos(
            @PageableDefault(size = 5, sort = { "id" }) Pageable pageable) {
        var pagina = repository.findAllByEnabledTrue(pageable).map(GetUsuarioDTO::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<GetUsuarioDTO> leerUnUsuario(@PathVariable String username) {
        Usuario usuario = (Usuario) repository.getReferenceByUsername(username);
        var datosUsuario = new GetUsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEnabled());
        return ResponseEntity.ok(datosUsuario);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetUsuarioDTO> leerUnUsuario(@PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        var datosUsuario = new GetUsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEnabled());
        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping("/{username}")
    @Transactional
    public ResponseEntity<GetUsuarioDTO> actualizarUsuario(@RequestBody @Valid PutUsuarioDTO actualizarUsuarioDTO,
            @PathVariable String username) {

        actualizarValidador.forEach(v -> v.validate(actualizarUsuarioDTO));
        
        Usuario usuario = (Usuario) repository.findByUsername(username);

        if (actualizarUsuarioDTO.password() != null) {
            usuario.actualizarUsuarioConPassword(actualizarUsuarioDTO, actualizarUsuarioDTO.password());

        } else {
            usuario.actualizarUsuario(actualizarUsuarioDTO);
        }

        var datosUsuario = new GetUsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getEnabled());
        return ResponseEntity.ok(datosUsuario);
    }

    @DeleteMapping("/{username}")
    @Transactional
    public ResponseEntity<?> eliminarUsuario(@PathVariable String username) {
        Usuario usuario = (Usuario) repository.findByUsername(username);
        usuario.eliminarUsuario();
        return ResponseEntity.noContent().build();
    }
}