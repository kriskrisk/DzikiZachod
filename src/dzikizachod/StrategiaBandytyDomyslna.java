package dzikizachod;

import java.util.HashSet;

public class StrategiaBandytyDomyslna extends StrategiaBandyty{
    public StrategiaBandytyDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();

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
