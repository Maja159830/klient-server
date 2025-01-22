package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Lokalizacja rejestru RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Pobranie stubu serwisu
            ZwierzetaService service = (ZwierzetaService) registry.lookup("ZwierzetaService");

            // Rejestracja klienta
            service.registerClient();

            // Wywołanie metody zdalnej
            List<Object> koty = service.getZwierzeta("kot");
            System.out.println("Pobrano koty: " + koty);

            List<Object> psy = service.getZwierzeta("pies");
            System.out.println("Pobrano psy: " + psy);

            List<Object> myszy = service.getZwierzeta("mysz");
            System.out.println("Pobrano myszy: " + myszy);

            // Symulacja działania klienta przez 10 sekund
            System.out.println("Klient pozostaje aktywny...");
            Thread.sleep(10000);

            // Wyrejestrowanie klienta
            service.unregisterClient();
            System.out.println("Klient zakończył działanie.");
        } catch (Exception e) {
            System.err.println("Błąd klienta RMI: " + e);
            e.printStackTrace();
        }
    }
}


