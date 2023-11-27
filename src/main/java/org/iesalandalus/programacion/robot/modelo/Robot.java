package org.iesalandalus.programacion.robot.modelo;

import javax.management.openmbean.OpenDataException;
import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Robot {

    private Coordenada coordenada;
    private Orientacion orientacion;
    private Zona zona;


    public Robot(Zona zona) {
        setZona(zona);


        this.coordenada = zona.getCentro();
        this.orientacion = Orientacion.NORTE;
    }

    public Robot(Zona zona, Orientacion orientacion) {
        this(zona);
        setOrientacion(orientacion);


    }

    public Robot(Zona zona, Orientacion orientacion, Coordenada coordenada) {
        this(zona, orientacion);
        setCoordenada(coordenada);

    }



    public Robot(){
        this.zona = new Zona();
        this.orientacion = Orientacion.NORTE;
        this.coordenada = this.zona.getCentro();

    }
    public Robot(Robot robot) {
        Objects.requireNonNull(robot, "El robot no puede ser nulo.");
        zona = robot.zona;
        orientacion = robot.orientacion;
        coordenada = robot.coordenada;
    }

    public Zona getZona() {
        return zona;
    }


    public void setZona(Zona zona) {
        if (zona == null) {
            throw new NullPointerException("La zona no puede ser nula.");
        }
        this.zona = zona;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    private void setOrientacion(Orientacion orientacion) {
        if (orientacion ==null)
            throw new NullPointerException("La orientación no puede ser nula.");
        this.orientacion = orientacion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    private void setCoordenada(Coordenada coordenada) {
        Objects.requireNonNull(coordenada,"La coordenada no puede ser nula.");
        if (!zona.pertenece(coordenada)) {
            throw new IllegalArgumentException("La coordenada no pertenece a la zona.");
        }

        this.coordenada = coordenada;
    }


    public void avanzar() throws OperationNotSupportedException {
        switch (orientacion) {
            case NORTE:
                if (coordenada.y() + 1 < zona.alto()) {
                    coordenada = new Coordenada(coordenada.x(), coordenada.y() + 1);
                } else {
                    throw new OperationNotSupportedException("No se puede avanzar, ya que se sale de la zona.");
                }
                break;
            case SUR:
                if (coordenada.y() - 1 >= 0) {
                    coordenada = new Coordenada(coordenada.x(), coordenada.y() - 1);
                } else {
                    throw new OperationNotSupportedException("No se puede avanzar, ya que se sale de la zona.");
                }
                break;
            case ESTE:
                if (coordenada.x() + 1 < zona.ancho()) {
                    coordenada = new Coordenada(coordenada.x() + 1, coordenada.y());
                } else {
                    throw new OperationNotSupportedException("No se puede avanzar, ya que se sale de la zona.");
                }
                break;
            case OESTE:
                if (coordenada.x() - 1 >= 0) {
                    coordenada = new Coordenada(coordenada.x() - 1, coordenada.y());
                } else {
                    throw new OperationNotSupportedException("No se puede avanzar, ya que se sale de la zona.");
                }
                break;
            case NORESTE:
                if (coordenada.x() + 1 < zona.ancho() && coordenada.y() + 1 < zona.alto()) {
                    coordenada = new Coordenada(coordenada.x() + 1, coordenada.y() + 1);
                } else {
                    throw new OperationNotSupportedException ("No se puede avanzar, ya que se sale de la zona.");                }
                break;
            case SURESTE:
                if (coordenada.x() + 1 < zona.ancho() && coordenada.y() - 1 >= 0) {
                    coordenada = new Coordenada(coordenada.x() + 1, coordenada.y() - 1);
                } else {
                    throw new OperationNotSupportedException ("No se puede avanzar, ya que se sale de la zona.");
                }
                break;
            case NOROESTE:
                if (coordenada.x() - 1 >= 0 && coordenada.y() + 1 < zona.alto()) {
                    coordenada = new Coordenada(coordenada.x() - 1, coordenada.y() + 1);
                } else {
                    throw new OperationNotSupportedException ("No se puede avanzar, ya que se sale de la zona.");
                }
                break;
            case SUROESTE:
                if (coordenada.x() - 1 >= 0 && coordenada.y() - 1 >= 0) {
                    coordenada = new Coordenada(coordenada.x() - 1, coordenada.y() - 1);
                } else {
                    throw new OperationNotSupportedException ("No se puede avanzar, ya que se sale de la zona.");
                }
                break;
            default:
                throw new OperationNotSupportedException("No se puede avanzar, ya que se sale de la zona.");
        }
    }



    public void girarALaDerecha(){
        switch (orientacion) {
            case NORTE -> {
                orientacion = Orientacion.NORESTE;
                break;
            }
            case NORESTE -> {
                orientacion = Orientacion.ESTE;
                break;
            }
            case ESTE ->  {
                orientacion = Orientacion.SURESTE;
                break;
            }
            case SURESTE ->  {
                orientacion = Orientacion.SUR;
                break;
            }
            case SUR ->  {
                orientacion = Orientacion.SUROESTE;
                break;
            }
            case SUROESTE ->  {
                orientacion = Orientacion.OESTE;
                break;
            }
            case OESTE ->  {
                orientacion = Orientacion.NOROESTE;
                break;
            }
            case NOROESTE ->  {
                orientacion = Orientacion.NORTE;
                break;
            }
        }
    }

    public void girarALaIzquierda(){
        switch (orientacion) {
            case NORTE -> {
                orientacion = Orientacion.NOROESTE;
                break;
            }
            case NOROESTE-> {
                orientacion = Orientacion.OESTE;
                break;
            }
            case OESTE ->  {
                orientacion = Orientacion.SUROESTE;
                break;
            }
            case SUROESTE ->  {
                orientacion = Orientacion.SUR;
                break;
            }
            case SUR ->  {
                orientacion = Orientacion.SURESTE;
                break;
            }
            case SURESTE ->  {
                orientacion = Orientacion.ESTE;
                break;
            }
            case ESTE ->  {
                orientacion = Orientacion.NORESTE;
                break;
            }
            case NORESTE ->  {
                orientacion = Orientacion.NORTE;
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(coordenada, robot.coordenada) && orientacion == robot.orientacion && Objects.equals(zona, robot.zona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordenada, orientacion, zona);
    }

    @Override
    public String toString() {
        return "Robot{" +
                "coordenada=" + coordenada +
                ", orientacion=" + orientacion +
                ", zona=" + zona +
                '}';
    }
}

