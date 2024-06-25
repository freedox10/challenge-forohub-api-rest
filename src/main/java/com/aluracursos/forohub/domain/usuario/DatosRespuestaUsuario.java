package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.perfil.Rol;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record DatosRespuestaUsuario(
        //Long id,
        String nombre,
        String email,
        //String contrasenia,
        Rol rol
) {


    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getRol());
    }
}
