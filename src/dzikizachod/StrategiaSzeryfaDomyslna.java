package dzikizachod;

import java.util.HashSet;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    public StrategiaSzeryfaDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        Czynnosc ruch = zwiekszanieZasiegu(gra);
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();

        if (ruch != null) {
            return ruch;
        }

        ruch = leczenie(gra);

        if (ruch != null) {
            return ruch;
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            if (!gra.getStrzeliliDoSzeryfa().isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(gra.getStrzeliliDoSzeryfa()));
            } else {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzNieSzeryfaZeZbioru(gra.getGracze()));
            }
        }

        return null;
    }

}
