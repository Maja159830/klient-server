package org.example;

import java.io.Serializable;

class Mysz implements Serializable {
    String kolor;
    Mysz(String kolor) { this.kolor = kolor; }
    @Override
    public String toString() { return "Mysz{kolor='" + kolor + "'}"; }
}
