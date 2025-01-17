package foro.alura.api.dominio.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long>{
    Page<Curso> findAllByActivoTrue(org.springframework.data.domain.Pageable pageable);
}