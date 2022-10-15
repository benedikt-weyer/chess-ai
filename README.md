# IS-P1



## Fragestellung:

### Was sind geeigenete Heuristiken für das Finden von guten Schachzügen?

## Ziel:
Finden und Vergleichen von geeigneten Heuristiken für das Finden von guten Schachzügen.
Dafür wird ein Schachprogramm geschrieben, welches die Heuristiken implementiert und diese miteinander vergleicht.
Der Vergleich erfolgt durch das Spielen von Spielen zwischen den Heuristiken und die Ergebnisse werden in einer Tabelle dargestellt.
Die Anzahl der Züge die berechnet werden, beschränken sich auf 3, damit wird sichergestellt, dass die Heuristiken nicht zu lange brauchen um einen Zug zu berechnen.


## Vorgehen:
### 1. Überlegen von geeigneten Heuristiken
### 2. Implementieren eines Schachprogramms
### 3. Implementieren der Heuristiken
### 4. Testen der Heuristiken
### 5. Vergleichen der Heuristiken

## 1. Überlegen von geeigneten Heuristiken

Die Heuristiken sollen die Qualität eines Zuges bewerten. Dafür werden die Züge in verschiedene Kategorien eingeteilt. Die Kategorien sind:
- Wert der Figuren
- Möglichkeiten des Gegners
- Position der Figuren
- Mattgefahr
- Mattgefahr des Gegners

