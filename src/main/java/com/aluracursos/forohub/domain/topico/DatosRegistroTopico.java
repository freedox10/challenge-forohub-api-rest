package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        //Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        //LocalDateTime fechaCreacion,
        //Estado estado,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId
) {
}
