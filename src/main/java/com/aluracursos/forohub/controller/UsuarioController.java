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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));

        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaUsuario);
        // Return 201 Created
        // URL donde encontrar al usuario
        // GET http://localhost:8080/usuarios/xx
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));
//        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuario::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
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
    public void eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornarDatosUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(usuario);
        return ResponseEntity.ok(datosUsuario);
    }

}
