package org.example;

import java.io.Serializable;

class Pies implements Serializable {
    String rasa;
    Pies(String rasa) { this.rasa = rasa; }
    @Override
    public String toString() { return "Pies{rasa='" + rasa + "'}"; }
}

