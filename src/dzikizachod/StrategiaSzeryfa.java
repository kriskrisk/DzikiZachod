package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class StrategiaSzeryfa extends Strategia {

    public Czynnosc leczenie(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();
        ArrayList<Gracz> aktualnyStanGry = gra.getGracze();
        int numerObecnegoGracza = gra.getAktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            }
        }

        return null;
    }
}
