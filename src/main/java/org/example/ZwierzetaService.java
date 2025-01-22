package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ZwierzetaService extends Remote {
    List<Object> getZwierzeta(String typ) throws RemoteException;

    // Rejestracja klienta
    void registerClient() throws RemoteException;

    // Wyrejestrowanie klienta
    void unregisterClient() throws RemoteException;
}
