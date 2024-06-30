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
    @Autowired
    private RespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosResponseRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta) {

        var response = service.registrarRespuesta(datosRegistroRespuesta);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuesta(@PageableDefault(size = 10) @SortDefault(sort = "fechaCreacion",
            direction = Sort.Direction.ASC) Pageable paginacion) {
        var response = service.listarRespuesta(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/solucion/{solucion}")
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestasPorSolucion(
            @PageableDefault(size = 10) Pageable paginacion, @PathVariable Boolean solucion) {

        var response = service.listarRespuestasPorSolucion(solucion, paginacion);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosResponseRespuesta> actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {

        var response = service.actualizarRespuesta(datosActualizarRespuesta);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosResponseRespuesta> retornarDatosRespuesta(@PathVariable Long id) {

        var response = service.retornarDatosRespuesta(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarRespuesta(@PathVariable Long id){

        service.eliminarRespuesta(id);

        return ResponseEntity.ok("respuesta eliminada");
    }

}
