package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.perfil.Rol;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String email,
        //String contrasenia,
        Rol rol
) {

    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRol());
    }
}
