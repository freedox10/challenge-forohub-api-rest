package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.perfil.Rol;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        String nombre,
        String email,
        String contrasenia,
        Rol rol
) {
}
