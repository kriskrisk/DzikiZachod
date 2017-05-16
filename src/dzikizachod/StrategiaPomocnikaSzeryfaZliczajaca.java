package dzikizachod;

import java.util.HashSet;

public class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa {
    public StrategiaPomocnikaSzeryfaZliczajaca() {}

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

            return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(podejrzani(kandydaci)));
        }

        return null;
    }
}
