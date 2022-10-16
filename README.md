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
Diese Aufgabe wurde von allen drei Gruppenmitgliedern geteilt und gemeinsam bearbeitet. Wobei Mirco und Julius weiterführende Recherchen durchgeführt haben.
### 2. Implementieren eines Schachprogramms
Dies wurde hauptsächlich von Benni durchgeführt. Er hat das Schachprogramm überwiegend alleine geschrieben, da er größere Erfahrung mit Schach hat.
### 3. Implementieren der Heuristiken
Diese Aufgabe wurde von allen drei Gruppenmitgliedern geteilt und gemeinsam bearbeitet. überwiegend wurde hier mit pair programming gearbeitet.
### 4. Testen der Heuristiken
Die Heuristiken wurden von allen drei Gruppenmitgliedern getestet. 
### 5. Vergleichen der Heuristiken
Die Heuristiken wurden gemeinsam verglichen und zusammengetragen.

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

### 3. und 4. also Implementieren und testen der Heuristiken
Wir haben mit den einfachsten Heuristiken angefangen, also wie viele Schachfiguren von und - wie viele Figuren vom Gegner 
noch stehen.
Weiß hat dabei mit der Heuristik gespielt und mit einer Depth von 1 geschaut was am meisten Sinn macht.
Schwarz hat mit der Random Schach AI gespielt. Oft konnte Weiß gewinnen aber meist kam es zu einem Unentschieden, da Weiß mit einer
tiefensuche bis depth = 1 nicht besonders weit in die Zukunft schauen konnte und daher auch nicht das Schachmatt erzwingen konnte.
Mit einer Tiefe von 2 war der Algorithmus sehr langsam.

#### Probleme:
Auch wenn die Heuristik sehr einfach ist, ist die Implementierung schwerer gewesen als gedacht. Die KI hat trotz
logisch korrekter Implementierung nicht immer das richtige Ergebnis geliefert. Uns fiel auf, dass mit einer Tiefe von 0 (also nur der
Beachtung der aktuellen Situation) immer der richtige Zug berechnet und gefunden wurde. Bei einer Tiefe von 2
wurde zwar der richtige Zug aus der Berechnung gefunden, aber
die Berechnung selbst schien nicht korrekt zu sein. Nach längerer Suche haben wir herausgefunden, dass unsere Implementierung des
Alphabeta-Pruning nicht korrekt war. Wir haben uns dann an die Implementierung von AlphaBeta Pruning aus nach dem in der Vorlesung gezeigten Beispiel gehalten. Dies hat dann auch das richtige Ergebnis geliefert.
Bei 2 gleichen AIs die gegeneinander spielen läuft es nahezu immer in einen Deadlock.
Aufgrund von vermutlich schlechter Optimierung der Berechnung ist die Berechnung ab einer Tiefe von 2 sehr langsam.

## Sources
- https://www.chessprogramming.org/Alpha-Beta
- https://www.quora.com/What-are-some-heuristics-for-quickly-evaluating-chess-positions
- https://www.youtube.com/watch?v=hCbfTm-b6iM
- https://www.youtube.com/watch?v=l-hh51ncgDI
