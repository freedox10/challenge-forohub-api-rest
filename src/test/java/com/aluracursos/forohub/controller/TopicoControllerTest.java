package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.DatosRespuestaTopico;
import com.aluracursos.forohub.domain.topico.Estado;
import com.aluracursos.forohub.domain.topico.TopicoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosRegistroTopico> datosRegistroTopicoJacksonTester;

    @Autowired
    private JacksonTester<DatosRespuestaTopico> datosRespuestaTopicoJacksonTester;

    @MockBean
    private TopicoService topicoService;

    @Test
    @DisplayName("deberia retornar estado http 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void registrarTopicoEscenario1() throws Exception{
        //given - dado //when - cuando
        var response = mvc.perform(post("/topicos")).andReturn().getResponse();

        //then - entonces
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("deberia retornar estado http 200 cuando los datos ingresados son validos")
    @WithMockUser
    void registrarTopicoEscenario2() throws Exception {
        //given

        var fecha = LocalDateTime.now().plusDays(-2);

        var datos = new DatosRespuestaTopico("Validaciones", "como comienzo", fecha, Estado.SOLUCIONADO, "Mirilis", "Springdoc");

        // when
        when(topicoService.registrarTopico(any())).thenReturn(datos);

        var response = mvc.perform(post("/topicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(datosRegistroTopicoJacksonTester.write(new DatosRegistroTopico("Validaciones", "como comienzo", 1L, 1L)).getJson())
        ).andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = datosRespuestaTopicoJacksonTester.write(datos).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

}
