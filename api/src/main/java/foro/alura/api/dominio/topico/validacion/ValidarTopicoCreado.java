package foro.alura.api.dominio.topico.validacion;

import foro.alura.api.dominio.topico.DTO.PostTopicoDTO;

public interface ValidarTopicoCreado {

    void validate(PostTopicoDTO data);
}