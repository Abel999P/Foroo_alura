package foro.alura.api.dominio.respuesta.DTO;

public record PutRespuestaDTO(
    String mensaje,
    Boolean solucion,
    Boolean borrado
) {
}