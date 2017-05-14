package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class StrategiaSzeryfa extends Strategia {
    public Czynnosc leczenie(HashSet<Akcja> posiadaneAkcje, ArrayList<Gracz> aktualnyStanGry, int numerObecnegoGracza) {
        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            int obecna = aktualnyStanGry.get(numerObecnegoGracza).getObecnaIloscPunktowZycia();
            int max =  aktualnyStanGry.get(numerObecnegoGracza).getMaxIloscPunktowZycia();

            if (obecna < max) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            } else if ()
        } else {
            return null;
        }
    }
}
