package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;

public record DatosResponseRespuesta(
        //Long id,
        String mensaje,
        String topico,
        //LocalDateTime fechaCreacion,
        String autor
        //Boolean solucion
) {

        public DatosResponseRespuesta(Respuesta respuesta) {
            this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
        }

}
