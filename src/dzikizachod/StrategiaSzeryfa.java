package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class StrategiaSzeryfa extends Strategia {

    public Czynnosc leczenie(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();
        ArrayList<Gracz> aktualnyStanGry = gra.gracze();
        int numerObecnegoGracza = gra.aktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            }
        }

        return null;
    }
}
