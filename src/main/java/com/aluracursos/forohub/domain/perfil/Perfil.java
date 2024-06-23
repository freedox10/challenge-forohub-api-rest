package com.aluracursos.forohub.domain.perfil;

public class Perfil {
    Long id;
    //String nombre;
    Rol rol;

    public Perfil(Long id, Rol rol) {
        this.id = id;
        this.rol = rol;
    }
}
