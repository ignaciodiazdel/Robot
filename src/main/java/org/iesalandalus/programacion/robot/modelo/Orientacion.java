package org.iesalandalus.programacion.robot.modelo;

enum Orientacion {

    NORTE("Norte"),
    NORESTE("Noreste"),
    ESTE("Este"),
    SURESTE("Sureste"),
    SUR("Sur"),
    SUROESTE("Suroeste"),
    OESTE("Oeste"),
    NOROESTE("Noroeste");

    private String nombre;

    Orientacion(String nombre) {
        this.nombre=nombre;
    }

    @Override
    public String toString() {
        return  nombre;
    }
}

