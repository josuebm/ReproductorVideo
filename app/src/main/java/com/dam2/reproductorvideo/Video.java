package com.dam2.reproductorvideo;

/**
 * Created by Josué on 23/01/2015.
 */
public class Video {

    String id, nombre, ruta, mime, fecha, tamano, duracion;

    public Video() {
    }

    public Video(String id, String nombre, String ruta, String mime, String fecha, String tamano, String duracion) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.mime = mime;
        this.fecha = fecha;
        this.tamano = tamano;
        this.duracion = duracion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ruta='" + ruta + '\'' +
                ", mime='" + mime + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tamano='" + tamano + '\'' +
                ", duracion='" + duracion + '\'' +
                '}';
    }
}
