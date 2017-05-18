package dzikizachod;

import java.util.LinkedList;

public class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa {
    public StrategiaPomocnikaSzeryfaZliczajaca() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();

        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (!wybierzDynamit(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzDynamit(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            LinkedList<Gracz> kandydaci = gra.wZasiegu(podejrzani(gra.zywiGracze()));

            return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(gra.wZasiegu(podejrzani(kandydaci))));
        }

        return new Czynnosc(Akcja.BRAK, null);
    }
}
