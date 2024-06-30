package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso) {
        var response = service.registrarCurso(datosRegistroCurso);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listadoCurso(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listadoCurso(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retornarDatosCurso(@PathVariable Long id) {
        var response = service.retornarDatosCurso(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        var response = service.actualizarCurso(datosActualizarCurso);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarCurso(@PathVariable Long id){
        service.eliminarCurso(id);
        return ResponseEntity.ok("curso eliminado");
    }

}
