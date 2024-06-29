package com.aluracursos.forohub.domain.respuesta.validaciones;

import com.aluracursos.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.aluracursos.forohub.domain.respuesta.DatosResponseRespuesta;
import com.aluracursos.forohub.domain.usuario.DatosRespuestaUsuario;

public interface ValidadorRespuesta {
    public void validar(DatosRegistroRespuesta datosRegistroRespuesta);
}
