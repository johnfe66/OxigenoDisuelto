package com.johnfe.oxigenodisuelto;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Johnfe Vargas on 2017-04-06.
 */

public class Datos {

    private String id;
    private Timestamp fecha;
    private String temperatura;
    private String oxigeno;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getOxigeno() {
        return oxigeno;
    }

    public void setOxigeno(String oxigeno) {
        this.oxigeno = oxigeno;
    }
}
