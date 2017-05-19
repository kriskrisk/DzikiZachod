package dzikizachod;

import java.util.LinkedList;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    public StrategiaSzeryfaDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();

        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            LinkedList<Gracz> cele = gra.wZasiegu(gra.strzeliliDoSzeryfa());

            if (!cele.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(gra.strzeliliDoSzeryfa()));
            } else {
                cele = gra.wZasiegu(new LinkedList<>(gra.zywiGracze()));
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(cele));
            }
        }

        return new Czynnosc(Akcja.BRAK, null);
    }

}
