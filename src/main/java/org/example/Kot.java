package org.example;

import java.io.Serializable;

class Kot implements Serializable {
    String imie;
    Kot(String imie) { this.imie = imie; }
    @Override
    public String toString() { return "Kot{imie='" + imie + "'}"; }
}
