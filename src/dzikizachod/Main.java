package dzikizachod;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Gracz> gracze = new ArrayList<>();
        gracze.add(new Szeryf());

        for(int i = 0; i < 3; i++) {
            gracze.add(new PomocnikSzeryfa());
        }

        for(int i = 0; i < 4; i++) {
            gracze.add(new Bandyta());
        }

        PulaAkcji pulaAkcji = new PulaAkcji();
        pulaAkcji.dodaj(Akcja.ULECZ, 20);
        pulaAkcji.dodaj(Akcja.STRZEL, 60);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 3);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 1);
        pulaAkcji.dodaj(Akcja.DYNAMIT, 1);

        Gra gra = new Gra();
        gra.rozgrywka(gracze, pulaAkcji);
    }
}
