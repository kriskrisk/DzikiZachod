package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Strategia {
    public Czynnosc zwiekszanieZasiegu(HashSet<Akcja> posiadaneAkcje) {
        if (posiadaneAkcje.contains(Akcja.ZASIEG_PLUS_DWA)) {
            return new Czynnosc(Akcja.ZASIEG_PLUS_DWA, null);
        } else if (posiadaneAkcje.contains(Akcja.ZASIEG_PLUS_JEDEN)) {
            return new Czynnosc(Akcja.ZASIEG_PLUS_JEDEN, null);
        } else {
            return null;
        }
    }

    public abstract Czynnosc leczenie(HashSet<Akcja> posiadaneAkcje, ArrayList<Gracz> aktualnyStanGry, int numerObecnegoGracza);
}
