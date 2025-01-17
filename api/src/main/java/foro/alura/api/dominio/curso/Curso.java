package foro.alura.api.dominio.curso;

import foro.alura.api.dominio.curso.DTO.PostCursoDTO;
import foro.alura.api.dominio.curso.DTO.PutCursoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cursos")
@Entity(name = "Curso")
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean activo;

    public Curso(PostCursoDTO postCursoDTO){
        this.nombre = postCursoDTO.nombre();
        this.categoria = postCursoDTO.categoria();
        this.activo = true;
    }
 
    public void actualizarCurso(PutCursoDTO putCursoDTO) {

        if(putCursoDTO.name() != null){
            this.nombre = putCursoDTO.name();
        }
        if (putCursoDTO.categoria() != null){
            this.categoria = putCursoDTO.categoria();
        }
        if (putCursoDTO.activo() != null){
            this.activo = putCursoDTO.activo();
        }
    }

    public void eliminarCurso(){
        this.activo = false;
    }
}