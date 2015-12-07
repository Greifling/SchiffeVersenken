import java.util.Scanner;
/**
 * Diese Klasse soll das Spiel "Schiffe versenken" simulieren.
 * 
 * @author
 * @author
 */

public class SchiffeVersenken {

    /**
     * An dieser Stelle wird das Feld des Spielers mitsamt der Positionen der
     * Schiffe deklariert. '.' = Wasser '#' = Schiff
     */
    private static char[][] spieler = {{'.', '#', '#', '#', '#', '.', '#', '#', '.', '.'}, // Reihe
                                                                                              // 0
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, // Reihe 1
        {'.', '.', '.', '.', '.', '.', '.', '#', '.', '#'}, // Reihe 2
        {'#', '#', '#', '.', '#', '.', '.', '#', '.', '#'}, // Reihe 3
        {'.', '.', '.', '.', '#', '.', '.', '#', '.', '.'}, // Reihe 4
        {'.', '#', '.', '.', '#', '.', '.', '.', '.', '.'}, // Reihe 5
        {'.', '#', '.', '.', '#', '.', '.', '#', '#', '.'}, // Reihe 6
        {'.', '.', '.', '.', '#', '.', '.', '.', '.', '.'}, // Reihe 7
        {'.', '.', '.', '.', '.', '.', '#', '#', '#', '#'}, // Reihe 8
        {'.', '#', '#', '.', '.', '.', '.', '.', '.', '.'} // Reihe 9
    };

    /**
     * An dieser stelle wird das Feld des Gegners deklariert. Da die Position
     * der Schiffe nicht bekannt ist, wird fuer alle Felder Wasser ('.')
     * definiert.
     */
    private static char[][] gegner = {{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe
                                                                                             // 0
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 1
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 2
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 3
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 4
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 5
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 6
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 7
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, // Reihe 8
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'} // Reihe 9
    };

    /**
     * Diese Methode gibt eines der Felder (Spieler oder Gegner) auf dem
     * Bildschirm aus.
     * 
     * @param grid
     *            = das auszugebende Feld
     */
    public static void print(char[][] grid) {
        int x = 0; // Spalte
        System.out.println("  0123456789"); // Kopfzeile
        while ((x) < 10) {
            System.out.print((char)(x+65) + " "); // Spaltenbezeichnung
            int y = 0; // Zeile
            while ((y) < 10) {
                System.out.print(grid[x][y]);
                y++;
            }
            x++;
            System.out.print("\n");
        }
        System.out.print("\n");
        return;
    }

    /**
     * Diese Methode fragt ab,welches Feld beschossen wird, und ob ein Treffer
     * erzielt wurde.
     */
    public static void attack() {
        Scanner scn1 = new Scanner(System.in);
        Scanner scn2 = new Scanner(System.in);
        int[] shot = new int[2];
        print(gegner); // Gegnerfeld ausgeben, um vorherige Spielzuege zu
                       // visualisieren
        System.out.println("Bitte geben Sie die Koordinaten ein (Form: <X Y> - X=Reihe, Y=Zeile).");
        shot[0] = scn1.nextInt();
        shot[1] = scn1.nextInt();
        if ((shot[0] < 0) || (shot[0] > 9) || (shot[1] < 0) || (shot[1] > 9)) { // falsche
                                                                                // Werte
                                                                                // abfangen
            System.out.println("Fehlerhafte Eingabe");
        } else { // Trefferabfrage
            System.out.println("Ziel= " + "Reihe: " + shot[0] + " Spalte: " + shot[1]);
            System.out.println("Wurde ein Treffer erzielt(y/n)?");
            String ergebnis;
            ergebnis = scn2.nextLine();
            // Ergebnis eintragen
            if (ergebnis.equals("y")) {
                gegner[shot[0]][shot[1]] = 'X';
                System.out.println("Treffer registriert.\n");
            } else if (ergebnis.equals("n")) {
                gegner[shot[0]][shot[1]] = 'O';
                System.out.println("Fehlschuss registriert.\n");
            } else {
                System.out.println("Keine gueltige Eingabe.");
            }
            print(gegner); // neuen Zustand anzeigen
        }
        scn1.close();
        scn2.close();
        return;
    }

    /**
     * Diese Methode prueft nach Angabe des vom Gegner beschossen Feldes, ob
     * damit ein Treffer erzielt wurde, und gibt dies aus.
     */
    public static void defend() {
        Scanner scn = new Scanner(System.in);
        int[] verteid = new int[2];
        System.out.println("Bitte geben Sie die Koordinaten ein (Form: <X Y> - X=Reihe(0-9), Y=Zeile(0-9)):");
        verteid[0] = scn.nextInt();
        verteid[1] = scn.nextInt();
        if (spieler[verteid[0]][verteid[1]] == '#') { // Schiff getroffen
            spieler[verteid[0]][verteid[1]] = 'X';
            System.out.println("Treffer auf " + "Reihe: " + verteid[0] + " Spalte: " + verteid[1] + "\n");
        } else { // verfehlt
            spieler[verteid[0]][verteid[1]] = 'O';
            System.out.println("Verfehlt auf " + "Reihe: " + verteid[0] + " Spalte: " + verteid[1] + "\n");
        }
        print(spieler); // neuen Zustand anzeigen
        scn.close();
        return;
    }

    /**
     * Diese Methode gibt den Menuetext aus.
     */
    public static void printMenu() {
        System.out.println("_________________________________________________\n");
        System.out.println("               Schiffe versenken");
        System.out.println("_________________________________________________\n");
        System.out.println("Moegliche Eingaben:\n");
        System.out.println("attack - Spielerzug ausfuehren");
        System.out.println("defend - Gegnerzug ausfuehren");
        System.out.println("help   - dieses Menue anzeigen");
        System.out.println("exit   - Spiel beenden");
        System.out.println("_________________________________________________\n");
        System.out.println("Positionen der Schiffe:\n");
        return;
    }

    /**
     * Die main-Funktion initialisiert das Spiel und ruft die Unterprogramme
     * auf, wenn der entsprechende Befehl eingegeben wird
     * 
     * @param args
     *            = noch unbenutzt
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String eingabe;
        boolean run = true; // boolean fuer while Schleife
        printMenu();
        print(spieler);

        while (run) {
            System.out.println("Bitte die naechste Aktion waehlen:");
            eingabe = s.nextLine();
            System.out.println("Eingabe: " + eingabe + "\n");

            switch (eingabe) {
                case "attack":// Spieler am Zug
                    attack();
                    break;
                case "defend":// Gegner am Zug
                    defend();
                    break;
                case "exit":// Spiel beenden
                    System.out.print("Spiel wird beendet...\n");
                    run = false;
                    break;
                case "help":
                    printMenu();
                    break;
                default:// fehlerhafte Eingabe
                    System.out.println("Keine gueltige Eingabe. <help> fuer mehr Infos.\n");
                    break;
            }
        }
        s.close();
        return;
    }
}
