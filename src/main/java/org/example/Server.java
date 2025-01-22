package org.example;

import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void initObjects(Map<String, Object> objects) {
        String[] imionaKotow = {"Lusia", "Lola", "Łatek", "Garfield", "Leon"};
        String[] rasyPsow = {"Buldog", "York", "Owczarek", "Terrier", "Jamnik"};
        String[] koloryMyszy = {"Biała", "Czarna", "Brązowa", "Łaciata", "Ruda"};

        for (int i = 0; i < 4; i++) {
            objects.put("kot_" + (i + 1), new Kot(imionaKotow[i]));
            objects.put("pies_" + (i + 1), new Pies(rasyPsow[i]));
            objects.put("mysz_" + (i + 1), new Mysz(koloryMyszy[i]));
        }
    }
}

