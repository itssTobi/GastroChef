# GastroChef Backend - ChefPoints System

Ein Spring Boot Backend für das GastroChef ChefPoints-Treueprogramm. Kunden können durch Rechnungscodes ChefPoints sammeln und diese gegen Produkte einlösen.

## 🍽️ Über das Projekt

Das ChefPoints-System ermöglicht:
- **Benutzerregistrierung und Login**
- **ChefPoints sammeln** durch Einlösen von Rechnungscodes
- **ChefPoints einlösen** gegen verfügbare Produkte/Prämien
- **Verlauf einsehen** aller gesammelten und eingelösten Punkte

## 🛠️ Technologien

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **H2 Database** (eingebettete Datenbank)
- **Lombok**
- **Maven**

## 🚀 Projekt starten

### Voraussetzungen

- Java 21 (JDK) installiert
- Maven (optional, Maven Wrapper ist enthalten)

### Starten mit Maven Wrapper

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Starten mit Maven

```bash
mvn spring-boot:run
```


## 🌐 Zugriff

Nach dem Start ist die Anwendung erreichbar unter:

- **API:** http://localhost:8080
- **H2 Datenbank-Konsole:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:file:./src/main/resources/gastro_chef_db`
  - Benutzer: `root`
  - Passwort: *(leer)*

## 📡 API-Endpunkte

### Benutzer (`/api/users`)
| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| POST | `/register` | Neuen Benutzer registrieren |
| POST | `/login` | Benutzer einloggen |
| GET | `/{userId}` | Benutzerinformationen abrufen |
| GET | `/{userId}/points` | ChefPoints-Stand abrufen |

### Rechnungscodes (`/api/bills`)
| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| POST | `/create` | Neuen Rechnungscode erstellen |
| POST | `/redeem` | Rechnungscode einlösen (Punkte gutschreiben) |
| GET | `/validate/{code}` | Code auf Gültigkeit prüfen |
| GET | `/all` | Alle Rechnungscodes anzeigen |

### Produkte (`/api/products`)
| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| GET | `/` | Alle verfügbaren Produkte |
| GET | `/{id}` | Einzelnes Produkt abrufen |
| POST | `/` | Neues Produkt erstellen |
| PUT | `/{id}` | Produkt aktualisieren |

### Einlösungen (`/api/redemptions`)
| Methode | Endpunkt | Beschreibung |
|---------|----------|--------------|
| POST | `/redeem` | Produkt gegen ChefPoints einlösen |
| GET | `/user/{userId}` | Einlösungsverlauf des Benutzers |

## 📁 Projektstruktur

```
src/main/java/com/gastrochef/
├── GastroChefBackendApplication.java   # Hauptanwendung
├── controller/                          # REST API Controller
├── dto/                                 # Data Transfer Objects
├── model/                               # JPA Entitäten
├── Repository/                          # Datenbank-Repositories
└── service/                             # Business-Logik
```

## 📝 Lizenz

Schulprojekt - POSDBMM 5. Klasse
