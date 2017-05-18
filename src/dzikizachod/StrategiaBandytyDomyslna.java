package dzikizachod;

import java.util.LinkedList;

public class StrategiaBandytyDomyslna extends StrategiaBandyty{
    public StrategiaBandytyDomyslna() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();

        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (!wybierzDynamit(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzDynamit(gra);
        }

        if (!strzelDoszeryfa(gra).akcja().equals(Akcja.BRAK)) {
            return strzelDoszeryfa(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            int aktualny = gra.aktualnyGracz();
            int lewoDystans = gra.dystansLewo(aktualny, 0);
            int prawoDystans = gra.dystansPrawo(aktualny, 0);
            int zasieg = gra.gracze().get(aktualny).zasięg();

            if (lewoDystans < prawoDystans) {
                LinkedList<Gracz> doWyboru = new LinkedList<>();
                int aktualnieRozpatrywany = gra.nastepnyGracz(aktualny);

                while (aktualnieRozpatrywany != 0) {
                    if (gra.gracze().get(aktualnieRozpatrywany).getClass().equals(PomocnikSzeryfa.class)) {
                        doWyboru.add(gra.gracze().get(aktualny));
                    }

                    aktualnieRozpatrywany = gra.nastepnyGracz(aktualnieRozpatrywany);
                }

                LinkedList<Gracz> kandydaci = gra.wZasiegu(doWyboru);

                if (kandydaci.size() != 0) {
                    return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
                }
            } else if (prawoDystans <= lewoDystans) {
                LinkedList<Gracz> doWyboru = new LinkedList<>();
                int aktualnieRozpatrywany = gra.poprzedniGracz(aktualny);

                while (aktualnieRozpatrywany != 0) {
                    if (gra.gracze().get(aktualnieRozpatrywany).getClass().equals(PomocnikSzeryfa.class)) {
                        doWyboru.add(gra.gracze().get(aktualny));
                    }

                    aktualnieRozpatrywany = gra.poprzedniGracz(aktualnieRozpatrywany);

                }

                LinkedList<Gracz> kandydaci = gra.wZasiegu(doWyboru);

                if (kandydaci.size() != 0) {
                    return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(kandydaci));
                }
            }
        }

        return new Czynnosc(Akcja.BRAK, null);
    }
}
