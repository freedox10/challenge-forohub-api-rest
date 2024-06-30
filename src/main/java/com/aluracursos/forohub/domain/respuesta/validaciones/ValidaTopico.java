package com.aluracursos.forohub.domain.respuesta.validaciones;

import com.aluracursos.forohub.domain.respuesta.DatosRegistroRespuesta;
import com.aluracursos.forohub.domain.topico.Estado;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaTopico implements ValidadorRegistrarRespuesta {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroRespuesta datosRegistroRespuesta) {
        if (datosRegistroRespuesta.topicoId() ==null ||
                !topicoRepository.existsById(datosRegistroRespuesta.topicoId())){
            throw new ValidacionDeIntegridad("No se encuentra Tópico");
        }
        Topico topico = topicoRepository.getReferenceById(datosRegistroRespuesta.topicoId());
        if (topico.getEstado() == Estado.CERRADO){
            throw new ValidacionDeIntegridad("el tópico se encuentra cerrado");
        }
    }
}
