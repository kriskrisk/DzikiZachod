package dzikizachod;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class StrategiaBandytySprytna extends StrategiaBandyty {
    public StrategiaBandytySprytna() {}

    private int ileStrzalowWTurze(Gra gra) {
        ListIterator<Akcja> iterator = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje().listIterator();
        int wynik = 0;

        while (iterator.hasNext()) {
            Akcja rozpatrywana = iterator.next();

            if (rozpatrywana.equals(Akcja.STRZEL)) {
                wynik++;
            }
        }

        return wynik;
    }

    public Czynnosc wybierzAkcje(Gra gra) {
        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (!wybierzDynamit(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzDynamit(gra);
        }

        if (!strzelDoszeryfa(gra).akcja().equals(Akcja.BRAK)) {
            return strzelDoszeryfa(gra);
        }

        if (gra.gracze().get(gra.aktualnyGracz()).liczbaZabitychBandytow() == 0) {
            LinkedList<Gracz> kandydaci = new LinkedList<>();
            int iloscMozliwychStrzalow = ileStrzalowWTurze(gra);

            for (int i = 1; i < gra.gracze().size(); i++) {
                Gracz rozpatrywany = gra.gracze().get(i);

                if (rozpatrywany.getClass().equals(Bandyta.class) &&
                        rozpatrywany.obecnaIloscPunktowZycia() <= iloscMozliwychStrzalow) {
                    kandydaci.add(rozpatrywany);
                }
            }

            if (!gra.wZasiegu(kandydaci).isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(gra.wZasiegu(kandydaci)));
            }
        } else {
            StrategiaBandytyDomyslna strategia = new StrategiaBandytyDomyslna();

            return strategia.wybierzAkcje(gra);
        }

        return new Czynnosc(Akcja.BRAK, null);
    }
}
