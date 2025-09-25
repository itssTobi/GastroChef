# GastroChef (POS/DBMM)

Dieses Projekt beschreibt ein System für eine **Benutzer-App** und eine **Restaurant-Webübersicht** zur Verwaltung von Punkten, Bestellungen und Rechnungscodes.

### Team 
- Gutmann
- Fellegger
- Hänsler
- Eichelberger
- Moser

## Anforderungsmanagement 
---

## Rollen

### Benutzer (Kunde)
- Nutzung der **App (Mobile/Web)**.
- Punkte sammeln, einsehen und einlösen. werden ChefPoints genannt
- Rechnungscodes eingeben, um Punkte zu erhalten.

### Restaurant (Admin)
- Nutzung der **Webübersicht**.
- Verwaltung von Punkten und Bestellungen.
- Übersicht über alle Benutzeraktivitäten.

---

## Funktionale Anforderungen

### Benutzer-App

- **Anmeldung**  
   *Als Benutzer möchte ich mich mit meinem selbst erstellten Konto anmelden können um die Funktionen nutzen zu können.*
  
- **Punkteübersicht anzeigen**  
   *Als Benutzer möchte ich meine aktuellen ChefPoints in der App sehen, um jederzeit zu wissen, wie viele Punkte ich bereits gesammelt habe.*

- **Rechnungscode eingeben**  
   *Als Benutzer möchte ich einen Rechnungscode eingeben können, um neue ChefPoints gutgeschrieben zu bekommen.*

- **Validierung des Codes**  
   *Als Benutzer möchte ich nach der Eingabe eine Rückmeldung bekommen, ob mein Rechnungscode gültig oder ungültig ist, um Fehler sofort zu erkennen.*

- **Punkte einlösen**  
   *Als Benutzer möchte ich meine ChefPoints einlösen können, um Rabatte oder Prämien im Restaurant zu erhalten.*

---

### Restaurant-Webübersicht
- **Punkteübersicht aller Benutzer**  
   *Als Restaurant-Admin möchte ich eine Liste aller Benutzer mit ihren aktuellen ChefPoints sehen, um deren Aktivität nachvollziehen zu können.*

- **Bestellübersicht einsehen**  
   *Als Restaurant-Admin möchte ich eine Übersicht aller Bestellungen mit den dazugehörigen Rechnungscodes haben, um den Überblick über Umsätze und Nutzeraktivitäten zu behalten.*

- **Warenkorb verwalten**  
   *Als Mitarbeiter möchte ich Waren in einen Warenkorb legen können, um Bestellungen für Gäste einfach zusammenzustellen.*

- **Rechnungscode generieren**  
   *Als Mitarbeiter möchte ich beim Abschluss einer Bestellung automatisch einen einmaligen Rechnungscode generiert bekommen, um diesen dem Gast für die ChefPoints zu geben.*

---

## Nicht-funktionale Anforderungen
- **Einmalige Rechnungscodes**  
   *Als System möchte ich sicherstellen, dass jeder generierte Rechnungscode nur einmal gültig ist, um Missbrauch und doppelte Punktevergaben zu verhindern.*

## Techstack:

| Ebene                    | Technologie                                   |
|--------------------------|-----------------------------------------------|
| **Backend**              | Spring Boot                                   |
| **Datenbank**            | H2                                            |
| **ORM / Mapping**        | JPA/Hibernate                                 |
| **Frontend Benutzer-App**| React Native                                  |
| **Frontend Admin-Panel** | React (Next.js)                               |
| **Build / Tools**        | Maven, JUnit 5, Spring Boot Test              |
