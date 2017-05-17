package dzikizachod;

import java.util.HashSet;

public class StrategiaPomocnikaSzeryfaDomyslna extends StrategiaPomocnikaSzeryfa {
    public StrategiaPomocnikaSzeryfaDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();

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
