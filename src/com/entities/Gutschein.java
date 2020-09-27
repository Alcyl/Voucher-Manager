package com.entities;

import java.math.BigInteger;
import java.util.Date;

public class Gutschein {

    private BigInteger nummer;
    private String spielerId;
    private Double auszahlbetrag;
    private Date datum;

    public BigInteger getNummer() {
        return nummer;
    }

    public void setNummer(BigInteger nummer) {
        this.nummer = nummer;
    }

    public String getSpielerId() {
        return spielerId;
    }

    public void setSpielerId(String spielerId) {
        this.spielerId = spielerId;
    }

    public double getAuszahlbetrag() {
        return auszahlbetrag;
    }

    public void setAuszahlbetrag(double auszahlbetrag) {
        this.auszahlbetrag = auszahlbetrag;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
