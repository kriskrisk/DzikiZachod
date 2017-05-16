package dzikizachod;

import java.util.HashSet;
import java.util.Iterator;

public class StrategiaBandytySprytna extends StrategiaBandyty {
    public StrategiaBandytySprytna() {}

    private int ileStrzalowWTurze(Gra gra) {
        Iterator<Akcja> iterator = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje().iterator();
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

        if (gra.getGracze().get(gra.getAktualnyGracz()).getLiczbaZabitychBandytow() == 0) {
            Gracz rozpatrywany;
            HashSet<Gracz> kandydaci = new HashSet<>();
            int iloscMozliwychStrzalow = ileStrzalowWTurze(gra);

            for (int i = 1; i < gra.getGracze().size(); i++) {
                rozpatrywany = gra.getGracze().get(i);

                if (rozpatrywany.getClass().equals(Bandyta.class) && rozpatrywany.getObecnaIloscPunktowZycia() <= iloscMozliwychStrzalow) {
                    kandydaci.add(rozpatrywany);
                }
            }

            if (!kandydaci.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
            }
        } else {
            int aktualny = gra.getAktualnyGracz();
            int lewoDystans = gra.dystansLewo(aktualny, 0);
            int prawoDystans = gra.dystansPrawo(aktualny, 0);
            int zasieg = gra.getGracze().get(aktualny).getZasięg();

            if (lewoDystans < prawoDystans) {
                HashSet<Gracz> doWyboru = new HashSet<>();

                for (int i = 0; i < zasieg; i++) {
                    aktualny = gra.nastepnyGracz(aktualny);

                    if (gra.getGracze().get(aktualny).getClass().equals(PomocnikSzeryfa.class)) {
                        doWyboru.add(gra.getGracze().get(aktualny));
                    }
                }

                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(doWyboru));
            } else if (prawoDystans <= lewoDystans) {
                HashSet<Gracz> doWyboru = new HashSet<>();

                for (int i = 0; i < zasieg; i++) {
                    aktualny = gra.poprzedniGracz(aktualny);

                    if (gra.getGracze().get(aktualny).getClass().equals(PomocnikSzeryfa.class)) {
                        doWyboru.add(gra.getGracze().get(aktualny));
                    }
                }

                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(doWyboru));
            }
        }

        return null;
    }
}
