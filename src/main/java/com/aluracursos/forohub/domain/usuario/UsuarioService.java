package com.aluracursos.forohub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaUsuario registrarUsuario(DatosRegistroUsuario datosRegistroUsuario) {

        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));

        return new DatosRespuestaUsuario(usuario);
    }

    public Page<DatosListadoUsuario> listadoUsuarios(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new);
    }

    public DatosRespuestaUsuario actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario) {

        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);

        return new DatosRespuestaUsuario(usuario);
    }

    public void eliminarUsuario(Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }

    public DatosRespuestaUsuario retornarDatosUsuario(Long id) {

        Usuario usuario = usuarioRepository.getReferenceById(id);

        return new DatosRespuestaUsuario(usuario);
    }

}
