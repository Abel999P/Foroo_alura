package foro.alura.api.dominio.topico.validacion;

import foro.alura.api.dominio.topico.DTO.PutTopicoDTO;

public interface ValidarTopicoActualizado {

    void validate(PutTopicoDTO data);
}