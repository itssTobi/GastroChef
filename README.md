# GastroChef (POS/DBMM)

Dieses Projekt beschreibt ein System für eine **Benutzer-App** und eine **Restaurant-Webübersicht** zur Verwaltung von Punkten, Bestellungen und Rechnungscodes.

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
- **Punkteübersicht**
  - Anzeige aktueller Punkte

- **Codeeingabe**
  - Eingabe von Rechnungscodes
  - Validierung (gültig/ungültig)

---

### Restaurant-Webübersicht
- **Punkteübersicht aller Benutzer**
  - Liste mit Benutzern und Punkten


- **Bestellübersicht**
  - Alle Bestellungen mit Rechnungscode
 
- **Warenkorb**
  - Mitarbeiter können Waren in den Warenkorb legen
  - Bei Bestellung wird ein Rechnungscode generiert

---

## Nicht-funktionale Anforderungen
- **Sicherheit**
  - Einmalige Rechnungscodes

## Techstack:

| Ebene                   | Technologie                                    |
|--------------------------|-----------------------------------------------|
| **Backend**              | Spring Boot 3 (Java 21), Spring Web (REST), Spring Data JPA, Spring Security |
| **Datenbank**            | H2 (In-Memory oder File-Modus)                |
| **ORM / Mapping**        | JPA/Hibernate, Lombok                         |
| **API-Dokumentation**    | OpenAPI (springdoc) – optional                |
| **Frontend Benutzer-App**| React Native (Expo) oder Flutter              |
| **Frontend Admin-Panel** | React (Next.js) oder Angular                  |
| **Build / Tools**        | Maven oder Gradle, JUnit 5, Spring Boot Test  |
