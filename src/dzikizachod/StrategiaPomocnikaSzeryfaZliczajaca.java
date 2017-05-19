package dzikizachod;

import java.util.LinkedList;

public class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa {
    public StrategiaPomocnikaSzeryfaZliczajaca() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();

        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            LinkedList<Gracz> kandydaci = gra.wZasiegu(gra.strzeliliDoSzeryfa());

            if (!kandydaci.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
            }

            kandydaci = gra.wZasiegu(podejrzani(gra.zywiGracze()));

            if (!kandydaci.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
            }
        }

        return wybierzDynamit(gra);
    }
}
