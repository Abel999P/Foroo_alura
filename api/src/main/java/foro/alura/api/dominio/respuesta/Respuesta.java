package foro.alura.api.dominio.respuesta;

import java.time.LocalDateTime;

import foro.alura.api.dominio.respuesta.DTO.PostRespuestaDTO;
import foro.alura.api.dominio.respuesta.DTO.PutRespuestaDTO;
import foro.alura.api.dominio.topico.Topico;
import foro.alura.api.dominio.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @Column(name = "fecha_d_creacion")
    private LocalDateTime fechaDeCreacion;
    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;
    private Boolean solucion;
    private Boolean borrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public Respuesta(PostRespuestaDTO postRespuestaDTO,Usuario usuario ,Topico topico){
        this.mensaje = postRespuestaDTO.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.ultimaActualizacion = LocalDateTime.now();
        this.solucion = false;
        this.borrado = false;
        this.usuario = usuario;
        this.topico = topico;
    }

    public void actualizarRespuesta(PutRespuestaDTO putRespuestaDTO){
        if(putRespuestaDTO.mensaje() != null){
            this.mensaje = putRespuestaDTO.mensaje();
        }
        if (putRespuestaDTO.solucion() != null){
            this.solucion = putRespuestaDTO.solucion();
        }
        this.ultimaActualizacion = LocalDateTime.now();
    }

    public void eliminarRespuesta(){
        this.borrado = true;
    }
}
