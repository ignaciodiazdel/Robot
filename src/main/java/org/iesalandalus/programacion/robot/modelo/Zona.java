package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public record Zona(int ancho, int alto) {

    public static final int ANCHO_MINIMO = 10;
    public static final int ALTO_MINIMO = 10;
    public static final int ANCHO_MAXIMO = 100;
    public static final int ALTO_MAXIMO = 100;

    private static Coordenada coordenada;

    public Zona {
        ValidarAncho(ancho);
        ValidarAlto(alto);
    }

    public Zona() {
        this(ANCHO_MINIMO, ALTO_MINIMO);
    }

    private void ValidarAncho(int ancho) {
        if (ancho < ANCHO_MINIMO || ancho > ANCHO_MAXIMO) {
            throw new IllegalArgumentException("Ancho no válido.");
        }
    }

    private void ValidarAlto(int alto) {
        if (alto < ALTO_MINIMO || alto > ALTO_MAXIMO) {
            throw new IllegalArgumentException("Alto no válido.");
        }
    }

    public Coordenada getCentro() {
        return new Coordenada(ancho / 2, alto / 2);
    }


        public boolean pertenece (Coordenada coordenada){
            Objects.requireNonNull(coordenada, "La coordenada no puede ser nula.");
            return perteneceX(coordenada.x()) && perteneceY(coordenada.y());
        }


        private boolean perteneceX ( int x){
            return (x >= 0 && x < ancho);
        }

        private boolean perteneceY ( int y){
            return (y >= 0 && y < alto);
        }



}



