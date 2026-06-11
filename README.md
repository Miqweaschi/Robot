# Progetto Programmazione 3 in Java
- Sviluppato da Nazzaro Tessitore e Luigi Gallo
---
## Traccia
- Si vuole sviluppare un algoritmo per il cammino di un robot in un labirinto. La stanza è pavimentata a tasselli quadrati (caselle) ed è dotata di pareti esterne e interne (vedi Figura 1 per un esempio). Il robot si può muovere nelle 8 direzioni adiacenti. Devono essere previsti almeno tre diversi scenari (livelli di difficoltà). Nella stanza sono presenti degli oggetti di diverso colore (red, green, yellow, cyan) che compaiono e scompaiono casualmente sul percorso. Il robot può assumenre quattro stati pursuit, evade, flee, seek. Se il robot si trova in prossimità di un oggetto cambia stato secondo lo schema della Macchina a Stati Finiti di Figura 2.
---
## Per ogni stato le strategie sono:

- **`pursuit e seek'** - va nella direzione dell’uscita di una singola cella alla volta

(usare l’algoritmo Artificial Ant Colony Optimization, vedi sotto)

- **`flee - va'** nella direzione dell’uscita di due celle alla volta

(usare l’algoritmo Artificial Ant Colony Optimization, vedi sotto)

- **`evade - avanza'** in maniera casuale di una singola cella
  Scrivere un programma per la gestione del labirinto. Il programma deve permettere di registrare il robot con il suo nome e cognome e visualizzare, ad ogni inizio e fine partita, la classifica dei risultati migliori ottenuti, da tutti i robot, in tutte le partite (minore numero di passi per raggiungere l’uscita). Visualizzare il percorso del robot dopo ogni passo, mostrando la stanza, la sua posizione e quella degli oggetti.


Ant Colony Optimization
L’algoritmo di Ant Colony Optimization è un algoritmo del tipo Swarm Intelligence. L’algoritmo fu introdotto per la prima volta nel 1992 da Marco Dorigo del Politecnico di Milano. Esso viene utilizzato per la risoluzione di problemi computazionali complessi. L’idea di base di questo algoritmo prende spunto dall’organizzazione di una colonia di formiche, si usa infatti un meccanismo di feedback positivo come una sorta di feromone virtuale, per rafforzare quelle parti di soluzione che contribuiscono alla risoluzione del problema. Per evitare invece la convergenza verso opzioni non idonee, viene utilizzato un meccanismo di feedback negativo (ad esempio l’evaporizzazione del feromone virtuale) che introduce una componente temporale nell’algoritmo. Il punto di forza dell’algoritmo consiste quindi nella creazione di un sistema intelligente distribuito.

Per i dettagli di sviluppo vedere: http://www.scholarpedia.org/article/Ant_colony_optimizatio

