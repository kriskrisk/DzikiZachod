package dzikizachod;

import java.util.LinkedList;
import java.util.ListIterator;

public class StrategiaPomocnikaSzeryfaDomyslna extends StrategiaPomocnikaSzeryfa {
    public StrategiaPomocnikaSzeryfaDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();

        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (!wybierzDynamit(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzDynamit(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            LinkedList<Gracz> zbior = new LinkedList<>(gra.zywiGracze());
            ListIterator<Gracz> iterator = zbior.listIterator();

            while (iterator.hasNext()) {
                Gracz gracz = iterator.next();

                if (gracz.getClass().equals(Szeryf.class)) {
                    zbior.remove(gracz);
                }

                break;
            }

            LinkedList<Gracz> kandydaci = gra.wZasiegu(zbior);

            if (!kandydaci.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
            }
        }

        return new Czynnosc(Akcja.BRAK, null);
    }
}
