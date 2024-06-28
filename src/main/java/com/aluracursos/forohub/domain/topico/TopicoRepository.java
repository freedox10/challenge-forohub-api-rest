package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.respuesta.Respuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("""
            select t from Topico t
            where t.estado=:estado
            order by id
            """)
    Page<Topico> buscarTopicosPorEstado(Estado estado, Pageable paginacion);
}
