package com.aluracursos.forohub.domain.curso;

import com.aluracursos.forohub.domain.usuario.DatosActualizarUsuario;
import com.aluracursos.forohub.domain.usuario.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
    }

    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {
        if (datosActualizarCurso.nombre() !=null){
            this.nombre = datosActualizarCurso.nombre();
        }
        if (datosActualizarCurso.categoria() !=null){
            this.categoria = datosActualizarCurso.categoria();
        }
    }

}
