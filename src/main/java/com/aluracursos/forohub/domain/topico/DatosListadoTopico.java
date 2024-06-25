package com.aluracursos.forohub.domain.topico;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        //LocalDateTime fechaCreacion,
        //Estado estado,
        String autor,
        String curso
) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }

}
