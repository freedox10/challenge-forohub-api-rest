package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.respuesta.*;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
//@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosResponseRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
                                                                     UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoRepository.getReferenceById(datosRegistroRespuesta.topicoId());
        if (topico.getEstado() == Estado.CERRADO){
            return ResponseEntity.unprocessableEntity().build();
        }
        Usuario autor = usuarioRepository.getReferenceById(datosRegistroRespuesta.autorId());

        Respuesta respuesta = new Respuesta(datosRegistroRespuesta, topico, autor);
        respuestaRepository.save(respuesta);

        topico.agregarRespuesta(respuesta);

        DatosResponseRespuesta datosRespuesta = new DatosResponseRespuesta(respuesta);

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
        // Return 201 Created
        // URL donde encontrar al topico
        // GET http://localhost:8080/topicos/xx
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuesta(@PageableDefault(size = 10) @SortDefault(sort = "fechaCreacion",
            direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosResponseRespuesta> actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {

        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        Topico topico = topicoRepository.getReferenceById(datosActualizarRespuesta.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(datosActualizarRespuesta.autorId());

        respuesta.actualizarDatos(datosActualizarRespuesta, topico, autor);

        return ResponseEntity.ok(new DatosResponseRespuesta(respuesta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosResponseRespuesta> retornarDatosRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var datosRespuesta = new DatosResponseRespuesta(respuesta);
        return ResponseEntity.ok(datosRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        //topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }

}
