package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Strategia {
    public Czynnosc zwiekszanieZasiegu(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();

        if (posiadaneAkcje.contains(Akcja.ZASIEG_PLUS_DWA)) {
            return new Czynnosc(Akcja.ZASIEG_PLUS_DWA, null);
        } else if (posiadaneAkcje.contains(Akcja.ZASIEG_PLUS_JEDEN)) {
            return new Czynnosc(Akcja.ZASIEG_PLUS_JEDEN, null);
        } else {
            return null;
        }
    }

    public abstract Czynnosc leczenie(Gra gra);

    public Czynnosc wybierzLeczenieLubZasieg(Gra gra) {
        Czynnosc ruch = zwiekszanieZasiegu(gra);

        if (ruch != null) {
            return ruch;
        }

        ruch = leczenie(gra);

        if (ruch != null) {
            return ruch;
        }

        return null;
    }
}
