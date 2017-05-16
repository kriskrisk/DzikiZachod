package dzikizachod;

import java.util.HashSet;

public class StrategiaBandytyDomyslna extends StrategiaBandyty{
    public StrategiaBandytyDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();

        if (wybierzLeczenieLubZasieg(gra) != null) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            if (!gra.strzeliliDoSzeryfa().isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(gra.strzeliliDoSzeryfa()));
            } else {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzNieSzeryfaZeZbioru(gra.getGracze()));
            }
        }

        return null;
    }
}
