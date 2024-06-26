package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record DatosResponseRespuesta(
        //Long id,
        String mensaje,
        Topico topico,
        //LocalDateTime fechaCreacion,
        Usuario autor
        //Boolean solucion
) {

    public record DatosRetornoRespuesta(String mensaje, String topico, String autor) {

        public DatosRetornoRespuesta(Respuesta respuesta) {
            this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
        }
    }

}
