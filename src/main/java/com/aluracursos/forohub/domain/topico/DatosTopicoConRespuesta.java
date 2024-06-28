package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.respuesta.DatosResponseRespuesta;
import org.springframework.data.domain.Page;

public record DatosTopicoConRespuesta(
        DatosRespuestaTopico topico,
        Page<DatosResponseRespuesta> respuestas
) {

}
