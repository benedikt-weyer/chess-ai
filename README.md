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
daher wäre Alpha Beta Pruning eine Möglichkeit, die man testen kann.
![](./rsc/2022-10-16%2012_50_08-Window.png)

### 3. und 4. also Implementieren und testen der Heuristiken
Wir haben mit den einfachsten Heuristiken angefangen, also wie viele Schachfiguren von und - wie viele Figuren vom Gegner 
noch stehen.
Weiß hat dabei mit der Heuristik gespielt und mit einer Depth von 1 geschaut was am meisten Sinn macht.
Schwarz hat mit der Random Schach AI gespielt. Oft konnte Weiß gewinnen aber meist kam es zu einem Unentschieden, da Weiß mit einer
tiefensuche bis depth = 1 nicht besonders weit in die Zukunft schauen konnte und daher auch nicht das Schachmatt erzwingen konnte.
Mit einer Tiefe von 2 war der Algorithmus sehr langsam.
Getestet haben wir die Heuristiken indem wir entweder selber gegen die AI gespielt haben oder die AI gegeneinander gespielt haben. Mit Hilfe von
Konsolenausgaben haben wir zusätzlich die Berechnung der Schritte überprüfen können, um zu sehen, ob der richtige Zug berechnet wurde. So konnten wir auch vergleichen,
ob der Zug tatsächlich mit dem übereinstimmt, der von der Heuristik berechnet werden sollte.

### 5. Vergleichen der Heuristiken
Die zufälligen Züge von RandomAI waren wie erwartet nicht wirklich gut, führten aber zu interessanten Spielen. 
ChessAI FirstMove war eigentlich nur zum Testen gedacht, da er immer den ersten Zug macht, der ihm in den Sinn kommt. Im Vergleich mit RandomAI war somit schlechter.
ChessAIEval war der erste Algorithmus, der eine Heuristik benutzte. Er war somit zwar besser als die anderen, aber nicht in dem Maße, wie wir es uns erhofft hatten. (Es wurden immer noch Züge gemacht, die nicht wirklich Sinn gemacht haben, bzw.
unnötig Figuren geopfert haben). Zusätzlich wurde er mit zunehmender Tiefe immer langsamer, das war zwar zu erwarten, dass es bei einer Tiefe von 2 schon nicht mehr zu einem Ende kommt war aber nicht so gut.
Aufgrund der schlechten Ergebnisse haben wir uns entschieden, dass wir pruning verwenden wollen. Wir haben uns für Alpha Beta Pruning entschieden, da es die einzige uns bekannte Pruning Methode ist, die auch für Schach funktioniert.
Das Pruning hat leider auch keine großen Verbesserungen gebracht, was wir an dieser Stelle mit der geringen Suchtiefe erklären können, sowie eine ggf. fehlerhafte Implementierung.

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
