package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.respuesta.DatosResponseRespuesta;
import com.aluracursos.forohub.domain.respuesta.RespuestaRepository;
import com.aluracursos.forohub.domain.topico.validaciones.ValidadorRegistrarTopico;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private List<ValidadorRegistrarTopico> validadorRegistrarTopicos;

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico){

        validadorRegistrarTopicos.forEach(v->v.validar(datosRegistroTopico));

        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroTopico.autorId());
        Curso curso = cursoRepository.getReferenceById(datosRegistroTopico.cursoId());

        Topico topico = new Topico(datosRegistroTopico, usuario, curso);

        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    public Page<DatosListadoTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    public Page<DatosListadoTopico> listarTopicosPorEstado(String estado, Pageable paginacion){
        if (estado.isBlank()){
            throw new ValidationException("estado inexistente");
        }
        if (!Estado.SOLUCIONADO.name().equals(estado) &&
                !Estado.SIN_SOLUCION.name().equals(estado) &&
                !Estado.SIN_RESPUESTA.name().equals(estado) &&
                !Estado.CERRADO.name().equals(estado)){
            throw new ValidationException("estado inexistente");
        }
        Estado actual = Estado.valueOf(estado);
        return topicoRepository.buscarTopicosPorEstado(actual, paginacion).map(DatosListadoTopico::new);
    }

    public DatosRespuestaTopico actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.id() ==null || !topicoRepository.existsById(datosActualizarTopico.id())){
            throw new ValidationException("tópico inexistente");
        }

        Usuario autor = usuarioRepository.getReferenceById(datosActualizarTopico.autorId());
        Curso curso = cursoRepository.getReferenceById(datosActualizarTopico.cursoId());

        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());

        topico.actualizarDatos(datosActualizarTopico, autor, curso);

        return new DatosRespuestaTopico(topico);
    }

    public DatosRespuestaTopico retornarDatosTopico(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return new DatosRespuestaTopico(topico);
    }

    public DatosTopicoConRespuesta retornarDatosTopicoConRespuestas(Long id, Pageable paginacion) {
        if (id ==null || !topicoRepository.existsById(id)){
            throw new ValidationException("tópico inexistente");
        }
        Topico topico = topicoRepository.getReferenceById(id);
        var respuestas = respuestaRepository.findAllByTopico(topico, paginacion).map(DatosResponseRespuesta::new);
        return new DatosTopicoConRespuesta(new DatosRespuestaTopico(topico), respuestas);
    }

    public void eliminarTopico(Long id){
        if (id ==null || !topicoRepository.existsById(id)){
            throw new ValidationException("tópico inexistente");
        }
        Topico topico = topicoRepository.getReferenceById(id);
        //topicoRepository.delete(topico);
        topico.cerrarTopico();
    }

}
