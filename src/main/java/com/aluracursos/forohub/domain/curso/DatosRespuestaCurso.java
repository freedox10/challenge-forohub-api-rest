package com.aluracursos.forohub.domain.curso;

public record DatosRespuestaCurso(
        //Long id,
        String nombre,
        String categoria
) {

    public DatosRespuestaCurso(Curso curso){
        this(curso.getNombre(), curso.getCategoria());
    }

}
