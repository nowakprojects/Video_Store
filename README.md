#PGS Software - Staż Java - Zadanie rekrutacyjne

## Moje rozwiązanie
Aby rozpocząć pracę z aplikacją proszę uruchomić program i wejśc w przeglądarce internetowej na adres: http://localhost:8080/

Do rozwiązania użyłem języka Java 8, a w szczególności frameworka Spring, wykorzystując umiejętności nabyte podczas Akademii Java. Było to także moje pierwsze starcie z jakąkolwiek formą front-endu - aplikacja udostępnia w formie interfejsu wszystkie wymagane funkcje. Został wykorzystany system budowania Maven.

Użyta baza danych, to Embedeed Apache Derby (z racji wymagania o nie instalowaniu sterowników) - jest dobra do fazy tworzenia oprogramowania i testowania. W razie potrzeby zleceniodawcy, może być zamieniona na inną, spełniającą jego wymagania. 
        
Napisałem także kilka testów jednostkowych, niestety nie zdążyłem ich dopracować. W przyszłości planowane jest także dodanie Spring Security, do zabezpieczeznia naszego systemu, aby uniemożliwić niepowołanym osobom (spoza pracowników wypożyczalni) dokonywania zmian.

    
Uwaga: W programie została użyta biblioteka Project Lombok - jeśli Twój IDE będzie sygnalizował błędy, program i tak powinien się skompilować. Znaczy to, że nie masz zainstalowanego pluginu, do odpowiedniego rozpoznawania adnotacji.

Miłego używania/ czytania kodu!

## Zadanie Java

### Problem
Prowadzimy wypożyczalnię filmów. Jesteśmy zobowiązani dostarczyć oprogramowanie wspomagające pracę wypożyczalni.

### Zadanie
Zadanie polega na stworzeniu aplikacji (mile widziana aplikacja okienkowa), która udostępni następujące funkcje:

* przedmioty zainteresowania
    * dodawanie
    * wyświetlenie dostępnych do wypozyczenia
* klienci
    * dodawanie
    * wyświetlenie listy
* wypożyczenia
    * zarejestrowanie wypożyczenia
    * wyświetlenie historii

*Uwaga: osoba obsługująca aplikację może czasem nie być w stanie zauważyć własnych pomyłek - aplikacja powinna być odpowiednio zabezpieczona na takie sytuacje!*

### Implementacja

Rozwiązanie powinno być zaimplementowane w języku Java. Kod powinien demonstrować dobre praktyki kodowania obiektowego. Mile widziana jest Java 8. Pamiętaj o pułapkach, jeśli się na nią zdecydujesz. Absolutnie dozwolone jest użycie bibliotek pomocniczych lub baz danych (uwagi poniżej). Ocenie podlegać będzie jakość kodu oraz funkcjonalność i jakość samej aplikacji. 


### Przygotowanie paczki

Dodatkowe punkty mogą zostać przyznane za wykorzystanie systemu budowania aplikacji.
Może być to Maven lub Gradle.

W katalogu głównym powinien znaleźć się skrypt budujący aplikację.

- `build.sh`: Może to być dosłownie:
  ```
  #!/bin/bash
  mvn clean package
  ```

  Jeśli skrypt będzie dostarczony, to sprawdzimy go na komputerze z zainstalowanymi:

  ```
  jdk8
  maven
  gradle
  ```
  Aplikacja nie powinna wymagać instalacji dodatkowego oprogramowania jak silniki baz danych. 
 
### Wysyłka

Preferowany sposób dostarczenia rozwiązania to link do repozytorium **git**. Może to być GitHub, GitLab lub hostowany git. Najważniejsze, by można było zaciągnąć źródła wywołując polecenie `git clone LINK`. Jeśli nie chcesz skorzystać z tej opcji, prosimy o link do pliku **zip**. Plik powinien być łatwy w identyfikacji; zawierać imię i nazwisko oraz rok: przykładowo _Tomasz_Kowalski_PGS_2016.zip_ Plik ten powinien zawierać jeden katalog z projektem i środku strukturę:

```
projekt
├── src
├── build.gradle/pom.xml
└── build.sh
```

W obydwu przypadkach oceniana będzie struktura dostarczonego projektu.


> Written with [StackEdit](https://stackedit.io/).


----------
