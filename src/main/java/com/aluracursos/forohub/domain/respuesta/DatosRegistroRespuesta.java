package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        //Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Boolean solucion,
        @NotNull
        Long topicoId,
        @NotNull
        Long autorId
        //LocalDateTime fechaCreacion,

) {
}
