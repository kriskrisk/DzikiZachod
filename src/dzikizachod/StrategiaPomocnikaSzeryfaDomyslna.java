package dzikizachod;

import java.util.HashSet;

public class StrategiaPomocnikaSzeryfaDomyslna extends StrategiaPomocnikaSzeryfa {
    public StrategiaPomocnikaSzeryfaDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();

        if (wybierzLeczenieLubZasieg(gra) != null) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (wybierzDynamit(gra) != null) {
            return wybierzDynamit(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            HashSet<Gracz> kandydaci = wZasiegu(gra);

            if (!kandydaci.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
            }
        }

        return null;
    }
}
