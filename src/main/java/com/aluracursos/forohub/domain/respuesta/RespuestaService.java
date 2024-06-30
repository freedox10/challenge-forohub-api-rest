package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.respuesta.validaciones.ValidadorRegistrarRespuesta;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    List<ValidadorRegistrarRespuesta> validadorRegistrarRespuestas;


    public DatosResponseRespuesta registrarRespuesta(DatosRegistroRespuesta datosRegistroRespuesta) {

        validadorRegistrarRespuestas.forEach(v->v.validar(datosRegistroRespuesta));

        Topico topico = topicoRepository.getReferenceById(datosRegistroRespuesta.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.autorId());
        Respuesta respuesta = new Respuesta(datosRegistroRespuesta, topico, autor);

        respuestaRepository.save(respuesta);

        topico.agregarRespuesta(respuesta);

        return new DatosResponseRespuesta(respuesta);
    }

    public Page<DatosListadoRespuesta> listarRespuesta(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new);
    }

    public Page<DatosListadoRespuesta> listarRespuestasPorSolucion(Boolean solucion, Pageable paginacion){
        if (solucion.describeConstable().isEmpty()){
            throw new ValidationException("solucion inexistente");
        }

        return respuestaRepository.findAllBySolucion(solucion, paginacion).map(DatosListadoRespuesta::new);
    }

    public DatosResponseRespuesta actualizarRespuesta(DatosActualizarRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.id() ==null || !respuestaRepository.existsById(datosActualizarRespuesta.id())){
            throw new ValidationException("respuesta inexistente");
        }

        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        Topico topico = topicoRepository.getReferenceById(datosActualizarRespuesta.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(datosActualizarRespuesta.autorId());

        respuesta.actualizarDatos(datosActualizarRespuesta, topico, autor);

        return new DatosResponseRespuesta(respuesta);
    }

    public DatosResponseRespuesta retornarDatosRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        return new DatosResponseRespuesta(respuesta);
    }

    public void eliminarRespuesta(Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
    }

}
