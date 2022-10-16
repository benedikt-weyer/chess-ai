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
1. Gesamtanzahl der Figuren
   - Der Zustand wird ausgewählt bei dem wir am meisten Figuren haben
   - Der Zustand wird ausgewählt, wo der Gegner am wenigsten Figuren hat
   - Alg wer hat weniger Figuren übrig?
   - Anzahl der eigenen Figuren - Anzahl der gegnerischen Figuren (wenn negativ dann ist der Gegner im Vorteil)
2. Wert der Figuren
   - Aus allen berechneten Zuständen den wo wir noch am meisten "Wert" haben
   - Aus allen berechneten Zuständen den wo der Gegner am wenigsten "Wert" hat
3. Möglichkeiten des Gegners
   - Wenige von unseren Figuren zu schlagen?
4. Position der Figuren
   - Wenn ich keinen Unterschied zwischen den Positionen meiner und der Figuren meines Gegners sehe, ist das Spiel ausgegeglichen
5. Mattgefahr 
6. Mattgefahr des Gegners 
7. Platz
   - Welche Seite beansprucht mehr Felder (hinter sich)
   - Wie viele Squares werden von deinen Figuren gehalten also können angegriffen werden?
   - Wie viele deiner Figuren können angegriffen werden
8. Initiative
   - Damit ist gemeint welcher Spieler die Figuren des anderen bedroht egal so von den Positionen her oder Figuren. Wer seinen "Plan" ausführen kann hat die Initiative
9. Maps pro FigurenTyp wo es lieber hinsoll, mit int werten pro Feld

### Vorschlag für den Wert der Figuren:
- Pawn = 1
- Bishop/knight = 3
- rook = 5
- queen = 9
- Falls König tot ist, ist der Wert der anderen Figuren egal. daher prüfen wir nur, ob er noch lebt sonst nichts

### Pruning:
- MinMax ist nicht machbar im Schach, da zu viele möglichkeiten abgewägt werden würden
daher Alpha Beta Pruning evtl.
![](./rsc/2022-10-16%2012_50_08-Window.png)
### Sources
https://www.quora.com/What-are-some-heuristics-for-quickly-evaluating-chess-positions \
https://www.youtube.com/watch?v=hCbfTm-b6iM \
https://www.youtube.com/watch?v=l-hh51ncgDI
