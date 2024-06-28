package com.aluracursos.forohub.domain.topico.validaciones;

import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteUsuario implements ValidadorRegistrarTopico{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosRegistroTopico datosRegistroTopico) {
        if (datosRegistroTopico.autorId() == null){
            throw new ValidationException("Debe asignar id de un usuario");
        }
        var usuario = usuarioRepository.existsById(datosRegistroTopico.autorId());
        if (!usuario){
            throw new ValidationException("Usuario no registrado");
        }
    }
}
