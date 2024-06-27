package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Estado;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private Boolean solucion;


    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Topico topico, Usuario autor) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.solucion = datosRegistroRespuesta.solucion();
        if (datosRegistroRespuesta.solucion()) {
            topico.setEstado(Estado.SOLUCIONADO);
        } else {
            topico.setEstado(Estado.SIN_SOLUCION);
        }
        this.topico = topico;
        this.autor = autor;
    }

    public void actualizarDatos(DatosActualizarRespuesta datosActualizar, Topico topico, Usuario autor) {
        if (datosActualizar.mensaje() != null) {
            this.mensaje = datosActualizar.mensaje();
        }
        if (datosActualizar.solucion() != this.solucion) {
            this.solucion = datosActualizar.solucion();
        }
        if (topico != null) {
            this.topico = topico;
        }
        if (autor != null) {
            this.autor = autor;
        }
    }

}
