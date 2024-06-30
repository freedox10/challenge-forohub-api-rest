package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuario.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        var response = service.registrarUsuario(datosRegistroUsuario);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
        var response = service.listadoUsuarios(paginacion);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        var response = service.actualizarUsuario(datosActualizarUsuario);
        return ResponseEntity.ok(response);
    }

//    // DELETE Logico, no borra, lo setea como inactivo
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
//        Usuario usuario = usuarioRepository.getReferenceById(id);
//        usuario.desactivarMedico();
//        return ResponseEntity.noContent().build();
//    }

    // DELETE en Base de Datos, borra registro definitivamente, no deseable en otros casos
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        service.eliminarUsuario(id);
        return ResponseEntity.ok("usuario eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornarDatosUsuario(@PathVariable Long id) {
        var response = service.retornarDatosUsuario(id);
        return ResponseEntity.ok(response);
    }

}
