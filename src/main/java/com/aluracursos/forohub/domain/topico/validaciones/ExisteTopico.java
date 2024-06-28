package com.aluracursos.forohub.domain.topico.validaciones;

import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteTopico implements ValidadorRegistrarTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datosRegistroTopico) {
        if (datosRegistroTopico.titulo() == null){
            throw new ValidationException("Debe dar un titulo");
        }
        if (datosRegistroTopico.mensaje() == null){
            throw new ValidationException("Debe crear un mensaje");
        }
        var titulo = topicoRepository.existsByTituloAndMensaje(
                datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());
        if (titulo !=null && titulo){
            throw new ValidationException("Ya existe un topico con ese titulo y mensaje");
        }
    }
}
