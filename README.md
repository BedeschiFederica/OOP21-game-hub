Email dei componenti: federica.bedeschi4@studio.unibo.it, alesja.delja@studio.unibo.it, silvia.furegato@studio.unibo.it

Il gruppo si pone come obiettivo quello di realizzare un'applicazione che raccoglie diversi giochi.

I giochi proposti saranno giocabili in singolo, per divertirsi e passare il tempo. L’obiettivo principale è quello di racchiudere quanti più giochi possibili, per dare la possibilità a tutti i giocatori di trovare quelli di proprio gradimento. La maggior parte sarà basata sul ragionamento, così da tenere allenata la mente.

Di seguito sono brevemente descritti alcuni dei giochi che verranno implementati:

    Campo minato: ha come campo di gioco una griglia nxn con m mine. Obiettivo del gioco è quello di scoprire tutto il campo senza prendere le mine; appena il giocatore preme una cella con la mina il gioco finisce.
    Flood it: è un gioco di strategia in cui l’obiettivo è colorare l’intera griglia di gioco dello stesso colore, col minor numero di mosse. Una mossa consiste nella colorazione di n celle, quelle a partire dalla cella in alto a sinistra che hanno tutte lo stesso colore, del colore della cella premuta nella mossa corrente, con l’obiettivo di includere alla mossa successiva quante più celle possibili.
    Legame numerico: sono presenti n quadrati con all’interno un numero. L’obiettivo del gioco è quello di collegare i quadrati tra loro, verticalmente o orizzontalmente, facendo attenzione, per ogni quadrato, ad effettuare il numero di collegamenti indicato all’interno dello stesso, e di non superare il massimo di due collegamenti per lato.
    
Nota: ci lasciamo la libertà di migliorare o modificare alcuni giochi rispetto alla loro classica implementazione conosciuta, ed eventualmente espandendo o aggiungendo alcuni meccanismi di gioco.


Funzionalità minimali ritenute obbligatorie:

    Implementazione dei seguenti giochi: Campo minato, Flood it, Legame numerico
    Creazione del menù principale
    Creazione di menù per ogni gioco, con possibilità di scelta da parte del giocatore, di eventuali parametri di gioco (esempio: dimensione della griglia di gioco)
    Possibilità di mettere in pausa il gioco, riavviandolo o tornando ai menù se il giocatore lo richiede
    Schermata di fine gioco, comprendente il risultato della partita o statistiche di gioco

 

Funzionalità opzionali:

    Implementazione di uno o più dei seguenti giochi: 2048, Mastermind, Tris
    Memorizzazione di record o statistiche di giochi su file, identificando i giocatori tramite username, e proponendo a video i record o le statistiche al fine di proporre sfide tra giocatori o stili di gioco differenti (esempio: challenge da completare)


Challenge principali:

    Creare l’applicazione in modo che possa essere facilmente estendibile per quanto riguarda il numero di giochi
    Uniformare l’applicazione standardizzando tutti gli aspetti dei vari giochi, così da renderla più chiara e intuitiva
    Generalizzare e astrarre i concetti di logica e grafica comuni ai giochi, riutilizzando più codice possibile


Suddivisione del lavoro:

    Bedeschi: implementazione del gioco Legame numerico
    Delja: implementazione del gioco Campo minato
    Furegato: implementazione del gioco Flood it

L’implementazione del gioco comprende sia la parte logica che quella grafica.

I menù e gli aspetti generali saranno ragionati insieme ed implementati in maniera equa in corso d’opera. Lo stesso avverrà per le funzionalità opzionali.
