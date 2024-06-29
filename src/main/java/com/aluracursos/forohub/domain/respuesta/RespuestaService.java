package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.respuesta.validaciones.ValidadorRespuesta;
import com.aluracursos.forohub.domain.topico.DatosRespuestaTopico;
import com.aluracursos.forohub.domain.topico.Estado;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;

    private List<ValidadorRespuesta> validadorRespuestas;

    public DatosResponseRespuesta registrarRespuesta(DatosRegistroRespuesta datosRegistroRespuesta) {

        validadorRespuestas.forEach(v->v.validar(datosRegistroRespuesta));

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
