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
    private static char[][] spieler = { { '.', '#', '#', '#', '#', '.', '#', '#', '.', '.' }, // Reihe
                                                                                              // 0
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' }, // Reihe 1
            { '.', '.', '.', '.', '.', '.', '.', '#', '.', '#' }, // Reihe 2
            { '#', '#', '#', '.', '#', '.', '.', '#', '.', '#' }, // Reihe 3
            { '.', '.', '.', '.', '#', '.', '.', '#', '.', '.' }, // Reihe 4
            { '.', '#', '.', '.', '#', '.', '.', '.', '.', '.' }, // Reihe 5
            { '.', '#', '.', '.', '#', '.', '.', '#', '#', '.' }, // Reihe 6
            { '.', '.', '.', '.', '#', '.', '.', '.', '.', '.' }, // Reihe 7
            { '.', '.', '.', '.', '.', '.', '#', '#', '#', '#' }, // Reihe 8
            { '.', '#', '#', '.', '.', '.', '.', '.', '.', '.' } // Reihe 9
    };

    /**
     * An dieser stelle wird das Feld des Gegners deklariert. Da die Position
     * der Schiffe nicht bekannt ist, wird fuer alle Felder Wasser ('.')
     * definiert.
     */
    private static char[][] gegner = { { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe
                                                                                             // 0
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 1
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 2
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 3
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 4
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 5
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 6
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 7
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' }, // Reihe 8
            { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' } // Reihe 9
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
            System.out.print((char) (x + 65) + " "); // Spaltenbezeichnung
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
        int[] angriff = input();
        System.out.println("Wurde ein Treffer erzielt(y/n)?");
        char in = scn1.nextLine().charAt(0);
        if (in == 'y') {
            gegner[angriff[0]][angriff[1]] = 'X';
            System.out.println("Treffer registriert. Sie sind erneut am Zug.\n");
            print(gegner);
            attack();
        } else if (in == 'n') {
            gegner[angriff[0]][angriff[1]] = 'O';
            System.out.println("Fehlschuss registriert.\n");
            print(gegner);
        } else {
            System.out.println("Keine gueltige Eingabe.");
        }
        return;
    }

    /**
     * Diese Methode prueft nach Angabe des vom Gegner beschossen Feldes, ob
     * damit ein Treffer erzielt wurde, und gibt dies aus.
     */
    public static void defend() {
        int[] verteid = input();
        if (spieler[verteid[0]][verteid[1]] == '#') { // Schiff getroffen
            spieler[verteid[0]][verteid[1]] = 'X';
            System.out.println("Du wurdest getroffen auf " + "Reihe: " + verteid[0] + " Spalte: " + verteid[1] + "\n");
            System.out.println("Du wirst erneut angegriffen.\n");
            print(spieler);
            defend();
        } else { // verfehlt
            spieler[verteid[0]][verteid[1]] = 'O';
            System.out.println("Verfehlt auf " + "Reihe: " + verteid[0] + " Spalte: " + verteid[1] + "\n");
            print(spieler);
        }
        return;
    }

    public static int[] input() {
        Scanner scn = new Scanner(System.in);
        int[] koords = new int[2];
        char shot1, shot2;
        System.out.println("Bitte geben Sie die Zeile an.");
        shot1 = scn.nextLine().charAt(0);
        if ((((int) shot1 > 47) && ((int) shot1 < 58))) {
            koords[0] = (int) shot1 - 48; // ASCII 0 bis 9
        } else if ((((int) shot1 > 64) && ((int) shot1 < 75))) {
            koords[0] = (int) shot1 - 65; // ASCII A bis J

        } else if ((((int) shot1 > 96) && ((int) shot1 < 107))) {
            koords[0] = (int) shot1 - 97; // ASCII a bis j

        } else {
            System.out.println("Fehlerhafte Eingabe");
            koords = input();
            return koords;
        }

        System.out.println("Bitte geben Sie die Spalte an.");
        shot2 = scn.nextLine().charAt(0);
        if ((((int) shot2 > 47) && ((int) shot2 < 58))) {
            koords[1] = (int) shot2 - 48; // ASCII 0 bis 9
        } else if ((((int) shot2 > 64) && ((int) shot2 < 75))) {
            koords[1] = (int) shot2 - 65; // ASCII A bis J

        } else if ((((int) shot2 > 96) && ((int) shot2 < 107))) {
            koords[1] = (int) shot2 - 97; // ASCII a bis j

        } else {
            System.out.println("Fehlerhafte Eingabe");
            koords = input();
            return koords;
        }
        return koords;
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
        System.out.println("Positionen der Schiffe:\n");
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
        return;
    }
}
