package dzikizachod;

import java.util.HashSet;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    public StrategiaSzeryfaDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();

        if (wybierzLeczenieLubZasieg(gra) != null) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            if (!gra.strzeliliDoSzeryfa().isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(gra.strzeliliDoSzeryfa()));
            } else {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzNieSzeryfaZeZbioru(gra.gracze()));
            }
        }

        return null;
    }

}
