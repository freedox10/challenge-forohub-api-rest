package com.aluracursos.forohub.domain.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public DatosRespuestaCurso registrarCurso(DatosRegistroCurso datosRegistroCurso) {

        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));

        return new DatosRespuestaCurso(curso);
    }

    public Page<DatosListadoCurso> listadoCurso(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
    }

    public DatosRespuestaCurso retornarDatosCurso(Long id) {

        Curso curso = cursoRepository.getReferenceById(id);

        return new DatosRespuestaCurso(curso);
    }

    public DatosRespuestaCurso actualizarCurso(DatosActualizarCurso datosActualizarCurso) {

        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);

        return new DatosRespuestaCurso(curso);
    }

    public void eliminarCurso(Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
    }

}