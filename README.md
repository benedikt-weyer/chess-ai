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
- - Aus allen berechneten Zuständen den wo wir noch am meisten "Wert" haben
- - Aus allen berechneten Zuständen den wo der Gegner am wenigsten "Wert" hat
- Möglichkeiten des Gegners
-  - Wenige von unseren Figuren zu schlagen?
- Position der Figuren
- - Wenn ich keinen Unterschied zwischen den Positionen meiner und der Figuren meines Gegners sehe ist das Spiel ausgegeglichen
- Mattgefahr
- Mattgefahr des Gegners
- Figurenanzahl
- - Der Zustand wird ausgewählt bei dem wir am meisten Figuren haben
- - Der Zustand wird ausgewählt wo der Gegner am wenigsten Figuren hat
- - Alg wer hat weniger Figuren übrig?
- Platz
- - Welche Seite beansprucht mehr Felder (hinter sich)
- - Wie viele Squares werden von deinen Figuren gehalten also können angegriffen werden?
- - Wie viele deiner Figuren können angegriffen werden
- Initiative
- - Damit ist gemeint welcher Spieler die Figuren des anderen bedroht egal so von den Positionen her oder Figuren. Wer seinen "Plan" ausführen kann hat die Initiative

Wert der Figuren Vorschlag
- Pawn = 1
- Bishop/knight = 3
- rook = 5
- queen = 9
- Geben wir dem King überhaupt einen Wert?


### Sources
https://www.quora.com/What-are-some-heuristics-for-quickly-evaluating-chess-positions
