package dzikizachod;

import java.util.HashSet;

public class StrategiaBandytyDomyslna extends StrategiaBandyty{
    public StrategiaBandytyDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();

        if (wybierzLeczenieLubZasieg(gra) != null) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (wybierzDynamit(gra) != null) {
            return wybierzDynamit(gra);
        }

        if (strzelDoszeryfa(gra) != null) {
            return strzelDoszeryfa(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
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
