package foro.alura.api.dominio.topico;

import java.time.LocalDateTime;


import foro.alura.api.dominio.curso.Curso;
import foro.alura.api.dominio.topico.DTO.PostTopicoDTO;
import foro.alura.api.dominio.topico.DTO.PutTopicoDTO;
import foro.alura.api.dominio.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topicos")
@Entity(name = "Topico")
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    
    @Column(name = "fecha_d_creacion")
    private LocalDateTime fechaDeCreacion;
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(PostTopicoDTO postTopicoDTO,Usuario usuario,Curso curso){
        this.titulo = postTopicoDTO.titulo();
        this.mensaje = postTopicoDTO.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.ultimaActualizacion = LocalDateTime.now();
        this.estado = Estado.OPEN;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarTopicoConCurso(PutTopicoDTO PutTopicoDTO, Curso curso) {
        if (PutTopicoDTO.titulo() != null){
            this.titulo = PutTopicoDTO.titulo();
        }
        if (PutTopicoDTO.mensaje() != null){
            this.mensaje = PutTopicoDTO.mensaje();
        }
        if (PutTopicoDTO.estado() != null){
            this.estado = PutTopicoDTO.estado();
        }
        if (PutTopicoDTO.cursoId() != null){
            this.curso = curso;
        }
        this.ultimaActualizacion = LocalDateTime.now();

    }

    public void actualizarTopico(PutTopicoDTO PutTopicoDTO){
        if (PutTopicoDTO.titulo() != null){
            this.titulo = PutTopicoDTO.titulo();
        }
        if (PutTopicoDTO.mensaje() != null){
            this.mensaje = PutTopicoDTO.mensaje();
        }
        if(PutTopicoDTO.estado() != null){
            this.estado = PutTopicoDTO.estado();
        }
        this.ultimaActualizacion = LocalDateTime.now();
    }

    public void eliminarTopico(){

        this.estado = Estado.DELETE;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }
}