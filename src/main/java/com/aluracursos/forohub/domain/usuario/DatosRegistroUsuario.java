package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.perfil.Perfil;
import com.aluracursos.forohub.domain.perfil.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String correoElectronico,
        @NotBlank
        String contrasenia,
        @NotNull
        Rol rol,
        @NotNull
        Perfil perfil
) {
}
