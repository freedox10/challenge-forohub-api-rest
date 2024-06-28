package com.aluracursos.forohub.domain.topico.validaciones;

import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteCurso implements ValidadorRegistrarTopico{

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validar(DatosRegistroTopico datosRegistroTopico) {
        if (datosRegistroTopico.cursoId() == null){
            throw new ValidationException("Debe asignar id de un curso");
        }
        var curso = cursoRepository.existsById(datosRegistroTopico.cursoId());
        if (!curso){
            throw new ValidationException("Curso no registrado");
        }
    }
}
