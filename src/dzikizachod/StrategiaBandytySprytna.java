package dzikizachod;

import java.util.HashSet;
import java.util.Iterator;

public class StrategiaBandytySprytna extends StrategiaBandyty {
    public StrategiaBandytySprytna() {}

    private int ileStrzalowWTurze(Gra gra) {
        Iterator<Akcja> iterator = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje().iterator();
        int wynik = 0;

        while (iterator.hasNext()) {
            iterator.next();

            if (iterator.equals(Akcja.STRZEL)) {
                wynik++;
            }
        }

        return wynik;
    }

    public Czynnosc wybierzAkcje(Gra gra) {
        if (wybierzLeczenieLubZasieg(gra) != null) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (wybierzDynamit(gra) != null) {
            return wybierzDynamit(gra);
        }

        if (strzelDoszeryfa(gra) != null) {
            return strzelDoszeryfa(gra);
        }

        if (gra.gracze().get(gra.aktualnyGracz()).liczbaZabitychBandytow() == 0) {
            Gracz rozpatrywany;
            HashSet<Gracz> kandydaci = new HashSet<>();
            int iloscMozliwychStrzalow = ileStrzalowWTurze(gra);

            for (int i = 1; i < gra.gracze().size(); i++) {
                rozpatrywany = gra.gracze().get(i);

                if (rozpatrywany.getClass().equals(Bandyta.class) && rozpatrywany.obecnaIloscPunktowZycia() <= iloscMozliwychStrzalow) {
                    kandydaci.add(rozpatrywany);
                }
            }

            if (!kandydaci.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
            }
        } else {
            int aktualny = gra.aktualnyGracz();
            int lewoDystans = gra.dystansLewo(aktualny, 0);
            int prawoDystans = gra.dystansPrawo(aktualny, 0);
            int zasieg = gra.gracze().get(aktualny).zasięg();

            if (lewoDystans < prawoDystans) {
                HashSet<Gracz> doWyboru = new HashSet<>();

                for (int i = 0; i < zasieg; i++) {
                    aktualny = gra.nastepnyGracz(aktualny);

                    if (gra.gracze().get(aktualny).getClass().equals(PomocnikSzeryfa.class)) {
                        doWyboru.add(gra.gracze().get(aktualny));
                    }
                }

                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(doWyboru));
            } else if (prawoDystans <= lewoDystans) {
                HashSet<Gracz> doWyboru = new HashSet<>();

                for (int i = 0; i < zasieg; i++) {
                    aktualny = gra.poprzedniGracz(aktualny);

                    if (gra.gracze().get(aktualny).getClass().equals(PomocnikSzeryfa.class)) {
                        doWyboru.add(gra.gracze().get(aktualny));
                    }
                }

                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(doWyboru));
            }
        }

        return null;
    }
}
