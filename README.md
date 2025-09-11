# GastroChef

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
  - Validierung (gültig/ungültig, bereits eingelöst)

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
