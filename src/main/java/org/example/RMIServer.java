package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RMIServer implements ZwierzetaService {
    private final Map<String, Object> objects;
    private static final AtomicInteger connectedClients = new AtomicInteger(0); // Licznik klientów

    public RMIServer(Map<String, Object> objects) {
        this.objects = objects;
    }

    @Override
    public List<Object> getZwierzeta(String typ) throws RemoteException {
        System.out.println("Obsługa żądania typu: " + typ);
        return objects.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(typ.toLowerCase() + "_"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public void registerClient() throws RemoteException {
        int currentClients = connectedClients.incrementAndGet();
        System.out.println("Nowy klient zarejestrowany. Liczba połączonych klientów: " + currentClients);
    }

    @Override
    public void unregisterClient() throws RemoteException {
        int currentClients = connectedClients.decrementAndGet();
        System.out.println("Klient wyrejestrowany. Liczba połączonych klientów: " + currentClients);
    }

    public static void main(String[] args) {
        try {
            // Inicjalizacja obiektów
            Map<String, Object> localObjects = new HashMap<>();
            Server.initObjects(localObjects);

            RMIServer server = new RMIServer(localObjects);

            // Eksportowanie obiektu RMI
            ZwierzetaService stub = (ZwierzetaService) UnicastRemoteObject.exportObject(server, 0);

            // Rejestracja w rejestrze RMI
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("ZwierzetaService", stub);

            System.out.println("Serwer RMI uruchomiony.");
        } catch (Exception e) {
            System.err.println("Błąd uruchamiania serwera RMI: " + e);
            e.printStackTrace();
        }
    }
}
