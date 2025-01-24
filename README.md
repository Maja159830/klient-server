Aplikacja Rozproszona z Wykorzystaniem RMI

1. Opis:

Projekt to aplikacja rozproszona składająca się z serwera oraz wielu klientów. Aplikacja wykorzystuje technologię RMI (Remote Method Invocation). System pozwala klientom na zdalne wykonywanie operacji na serwerze (w tymprzypadku  pobieranie danych o zwierzętach takich jak koty, psy czy myszy)

Aplikacja została zaprojektowana jako symulacja systemu, w którym serwer przechowuje informacje o różnych typach obiektów, a klienci mogą żądać ich pobrania.

2. Architektura

System składa się z:

- Serwera (RmiServer):

SErwer udostępnia zdalne metody umożliwiające klientom rejestrację, wyrejestrowanie oraz pobieranie danych, przechowuje kolekcję obiektów (zwierząt), obsługuje rejestrację w rejestrze RMI.

- Interfejsu zdalnego (ZwierzetaService):

Deklaruje metody zdalne dostępne dla klientów.

Metody:

registerClient() - rejestracja klienta

unregisterClient() - wyrejestrowanie klienta

getZwierzeta(String typ) - pobranie listy obiektów określonego typu

- Klienców (RMIClient, RMIClientTest):

Komunikują się z serwerem za pośrednictwem RMI, wysyłają żądania dotyczące pobierania danych oraz rejestracji/wyrejestrowania, obsługują wielowątkowość, co pozwala na uruchamianie wielu klientów jednocześnie.

3. Protokół komunikacyjny

RMI (Remote Method Invocation)

Java RMI to mechanizm umożliwiający wywoływanie metod obiektów znajdujących się na innych maszynach w sieci. W projekcie RMI zostało użyte do:

- Umożliwienia klientom wykonywania zdalnych metod serwera.

- Przesyłania danych (obiektów zwierząt) w sposób transparentny dla użytkownika.

- Rejestracji i wyrejestrowania klientów na serwerze.

RMI wykorzystuje poniższe elementy:

- Rejestr RMI do rejestrowania zdalnych obiektów.

- Stub i Skeleton do komunikacji między klientem a serwerem.

- Serializacja do przesyłania obiektów między JVM.

Mechanizmy synchronizacji

Serwer zarządza liczbą aktywnych klientów i synchronizuje dostęp do danych za pomocą zmiennych współdzielonych oraz metod monitorujących (synchronized).

4. Narzędzia i biblioteki

a) Java Standard Edition (JSE):

Podstawowa biblioteka Java dostarcza wszystkie wymagane klasy i narzędzia do implementacji RMI, w tym:

- java.rmi (Registry, Remote, UnicastRemoteObject)

- java.util.concurrent (ExecutorService do obsługi wielowątkowości)

- java.util (List, Collections)

b) RMI Registry:

- Narzędzie do rejestracji zdalnych obiektów serwera, umożliwiające ich odnajdywanie przez klientów.

5. Instrukcja uruchomienia

Uruchomienie serwera:

 I. Skonfiguruj i uruchom klasę RmiServer w IntelliJ IDEA.

 II. Upewnij się, że serwer jest zarejestrowany w RMI Registry.

Uruchomienie klientów:

 I. Skonfiguruj i uruchom klasę RMIClientTest, która uruchamia wielu klientów jednocześnie.

 II. Klienci rejestrują się na serwerze, wykonują żądania i wyrejestrują się po zakończeniu działania.

Testowanie:

Obserwuj logi w konsoli serwera oraz klientów, aby zweryfikować poprawność działania.

Przykładowe logi

Serwer:
Serwer uruchomiony...
Klient zarejestrowany. Aktualna liczba klientów: 1
Klient wyrejestrowany. Aktualna liczba klientów: 0

Klient:
Klient 1 pobrał koty: [Kot{imie='Garfield'}, Kot{imie='Lusia'}]
Klient 1 pobrał psy: [Pies{rasa='York'}, Pies{rasa='Buldog'}]
Klient 1 pobrał myszy: [Mysz{kolor='Czarna'}, Mysz{kolor='Biała'}]
Klient 1 pozostaje aktywny...
Klient 1 zakończył działanie.
