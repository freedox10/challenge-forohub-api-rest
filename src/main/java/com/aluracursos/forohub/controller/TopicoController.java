package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.*;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    //El @Operación la anotación se usa para describir una sola operación. Una operación es una combinación única de una ruta y un método HTTP.
    @Operation(
            summary = "registra un tópico",
            description = "debe existir autor y curso",
            tags = { "topico", "post" })
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) throws ValidacionDeIntegridad {

        var response = service.registrarTopico(datosRegistroTopico);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@PageableDefault(size = 10) @SortDefault(sort = "fechaCreacion",
            direction = Sort.Direction.ASC) Pageable paginacion) {
        var response = service.listarTopicos(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicosPorEstado(
            @PageableDefault(size = 10) Pageable paginacion, @PathVariable String estado) {

        var response = service.listarTopicosPorEstado(estado, paginacion);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        var response = service.actualizarTopico(datosActualizarTopico);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id) {
        var response = service.retornarDatosTopico(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}+R")
    public ResponseEntity retornarDatosTopicoConRespuestas(@PageableDefault(size = 10) Pageable paginacion,
                                                           @PathVariable Long id){
        var response = service.retornarDatosTopicoConRespuestas(id, paginacion);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id){
        service.eliminarTopico(id);
        return ResponseEntity.ok("tópico cerrado");
    }

}
