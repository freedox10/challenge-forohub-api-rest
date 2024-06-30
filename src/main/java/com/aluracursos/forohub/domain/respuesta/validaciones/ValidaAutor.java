package com.aluracursos.forohub.domain.respuesta.validaciones;

import com.aluracursos.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaAutor implements ValidadorRegistrarRespuesta {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosRegistroRespuesta datosRegistroRespuesta) {
        if (datosRegistroRespuesta.autorId() ==null || !usuarioRepository.existsById(datosRegistroRespuesta.autorId())){
            throw new ValidacionDeIntegridad("No se encuentra Autor");
        }
    }
}
