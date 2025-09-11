# GastroChef

## Anforderungsmanagement 

# 🍽️ Punkte- & Treuesystem

Dieses Projekt beschreibt ein System für eine **Benutzer-App** und eine **Restaurant-Webübersicht** zur Verwaltung von Punkten, Bestellungen und Rechnungscodes.

---

## Rollen

### Benutzer (Kunde)
- Nutzung der **App (Mobile/Web)**.
- Punkte sammeln, einsehen und einlösen.
- Rechnungscodes eingeben, um Punkte zu erhalten.

### Restaurant (Admin)
- Nutzung der **Webübersicht**.
- Verwaltung von Punkten und Bestellungen.
- Übersicht über alle Benutzeraktivitäten.

---

## Funktionale Anforderungen

### Benutzer-App
- **Punkteübersicht**
  - Anzeige aktueller Punkte.
  - Historie von Einlösungen und Gutschriften.

- **Codeeingabe**
  - Eingabe von Rechnungscodes.
  - Validierung (gültig/ungültig, bereits eingelöst).
  - Automatische Punktegutschrift.

- **Warenkorb (optional)**
  - Nutzer kann Waren in den Warenkorb legen.
  - Bei Bestellung wird ein Rechnungscode generiert.
  - Punkte können damit eingelöst werden.

---

### Restaurant-Webübersicht
- **Punkteübersicht aller Benutzer**
  - Gesamtübersicht mit Filter (Benutzer, Zeitraum).
  - Export (z. B. CSV/Excel).

- **Bestellübersicht**
  - Alle Bestellungen mit Rechnungscode.
  - Status: *offen*, *eingelöst*, *abgeschlossen*.
  - Verwaltung von Codes (stornieren, manuell Punkte gutschreiben).

---

## Nicht-funktionale Anforderungen
- **Sicherheit**
  - Einmalige Rechnungscodes, verschlüsselte Speicherung.
- **Performance**
  - Codevalidierung < 2 Sekunden.
- **Plattformen**
  - Mobile App (iOS/Android).
  - Web-App für Restaurant.
- **Skalierbarkeit**
  - Unterstützung vieler Benutzer & Transaktionen parallel.
