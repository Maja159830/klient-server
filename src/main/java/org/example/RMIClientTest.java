package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RMIClientTest {
    public static void main(String[] args) {
        // Tworzenie puli wątków do obsługi klientów
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Uruchamianie czterech klientów
        for (int i = 1; i <= 4; i++) {
            final int clientId = i;
            executorService.submit(() -> {
                try {
                    // Lokalizacja rejestru RMI
                    Registry registry = LocateRegistry.getRegistry("localhost", 1099);

                    // Pobranie stubu serwisu
                    ZwierzetaService service = (ZwierzetaService) registry.lookup("ZwierzetaService");

                    // Rejestracja klienta
                    service.registerClient();

                    // Wywołanie metod zdalnych
                    List<Object> koty = service.getZwierzeta("kot");
                    Collections.shuffle(koty); // Randomizacja listy
                    System.out.println("Klient " + clientId + " pobrał koty: " + koty);

                    List<Object> psy = service.getZwierzeta("pies");
                    Collections.shuffle(psy); // Randomizacja listy
                    System.out.println("Klient " + clientId + " pobrał psy: " + psy);

                    List<Object> myszy = service.getZwierzeta("mysz");
                    Collections.shuffle(myszy); // Randomizacja listy
                    System.out.println("Klient " + clientId + " pobrał myszy: " + myszy);

                    // Symulacja działania klienta przez 10 sekund
                    System.out.println("Klient " + clientId + " pozostaje aktywny...");
                    Thread.sleep(10000);

                    // Wyrejestrowanie klienta
                    service.unregisterClient();
                    System.out.println("Klient " + clientId + " zakończył działanie.");
                } catch (Exception e) {
                    System.err.println("Błąd klienta " + clientId + ": " + e);
                    e.printStackTrace();
                }
            });
        }

        // Zamykanie puli wątków po zakończeniu
        executorService.shutdown();
    }
}
