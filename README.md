# ⚡ QuRadar - Traffic Monitoring System

An extensible, clean traffic surveillance and violation enforcement system built in pure Java. `QuRadar` processes real-time radar payload observations (vehicle classification, license plate, speed, timestamp, seatbelt compliance), evaluates dynamic traffic safety rules, issues itemized fine notices, and provides real-time audit statistics.

---

## 🌟 Key Features

- 🚗 **Multi-Vehicle Classification**: Handles observations for Private Cars, Trucks, Buses, and custom vehicle categories.
- 📐 **Extensible Rule Engine (Open-Closed Principle)**: Built using the Strategy Pattern (`Rule` interface). New traffic safety rules can be registered dynamically without modifying core system logic.
- 🧾 **Itemized Fine Generation**: Generates and prints formatted fine tickets specifying infraction details and fees in EGP.
- 📊 **System Auditing & Analytics**:
  - `getAllPossibleFines()`: Aggregates fines per vehicle plate number with total fee amounts.
  - `getAllViolatedRulesWithCount()`: Tracks infraction counts for every registered traffic rule.

---

## 📐 Architecture & Extensibility

```
                                 +---------------------+
                                 |  RadarObservation   |
                                 +---------------------+
                                            |
                                            v
+-------------------+   addRule()   +-------------------+
|  Rule (Interface) | ----------->  |      QuRadar      |
+-------------------+               +-------------------+
  |               |                           |
  v               v                           v
SeatbeltRule    SpeedLimitRule          Fine / Violation Output
```

---

## 🚀 Quick Start

### Prerequisites
- **Java Development Kit (JDK 11 or higher)**

### Compile & Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/DarkStrox/QUANTUM-Radar.git
   cd QUANTUM-Radar
   ```

2. **Compile the Java source files**:
   ```bash
   javac *.java
   ```

3. **Run the Demonstration**:
   ```bash
   java Main
   ```

---

## 📋 Sample Fine Output

```text
Traffic for car ABC1234
Total amount: 400 EGP
Violations:
- Seatbelt not fastned : 100 EGP
- speed of 94 exceeded max allowed 80 : 300 EGP
```
