package org.example;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Random;

public class Klient implements Runnable {
    // Identyfikator klienta i lista żądań
    private final String id;
    private final String[] requests;
    // Konstruktor klienta
    public Klient(String id, String[] requests) {
        this.id = id;
        this.requests = requests;
    }
    @Override
    public void run() {
        try {
            // Nawiązanie połączenia z serwerem
            Socket socket = new Socket("localhost", 8000);
            System.out.println("Klient " + id + " połączony z serwerem.");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            for (String request : requests) {
                out.println(request);
                try {
                    // Odczyt odpowiedzi od serwera
                    List<Object> response = (List<Object>) in.readObject();
                    System.out.println("Klient " + id + " - Odpowiedź serwera dla " + request + ":");
                    for (Object obj : response) {
                        // Wyświetlenie odpowiedzi
                        System.out.println("Klient " + id + ": " + obj);
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Klient " + id + " - Błąd podczas odczytu obiektu: " + e.getMessage());
                } catch (ClassCastException e) {
                    System.out.println("Klient " + id + " - Nie można zrzutować odebranego obiektu na żądaną klasę: " + e.getMessage());
                }
                System.out.println();

                // Dodajemy losowe opóźnienie między zapytaniami
                Thread.sleep(new Random().nextInt(2000));
            }

            socket.close();
        } catch (IOException | InterruptedException e) {
            System.err.println("Klient " + id + " - Błąd podczas komunikacji z serwerem: " + e);
        }
    }
}
