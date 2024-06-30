package com.aluracursos.forohub.domain.respuesta.validaciones;

import com.aluracursos.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.aluracursos.forohub.domain.respuesta.RespuestaRepository;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaRespuesta implements ValidadorRegistrarRespuesta {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Override
    public void validar(DatosRegistroRespuesta datosRegistroRespuesta) {
        if (datosRegistroRespuesta.mensaje() == null){
            throw new ValidacionDeIntegridad("no proporcionó un mensaje");
        }
        if (datosRegistroRespuesta.topicoId() ==null){
            throw new ValidacionDeIntegridad("no proporcionó un topico");
        }
        if (datosRegistroRespuesta.autorId() ==null){
            throw new ValidacionDeIntegridad("no proporcionó un autor");
        }
        var autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.autorId());
        var topico = topicoRepository.getReferenceById(datosRegistroRespuesta.topicoId());
        var busqueda = respuestaRepository.existsByMensajeAndTopicoAndAutor(datosRegistroRespuesta.mensaje(), topico, autor);
        if (busqueda){
            throw new ValidacionDeIntegridad("Ya existe este mensaje con mismo topico y autor");
        }
    }
}
