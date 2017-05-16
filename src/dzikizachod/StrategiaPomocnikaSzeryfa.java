package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class StrategiaPomocnikaSzeryfa extends Strategia {
    public Czynnosc leczenie(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();
        ArrayList<Gracz> aktualnyStanGry = gra.getGracze();
        int numerObecnegoGracza = gra.getAktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if ((numerObecnegoGracza == 1 || numerObecnegoGracza == gra.getGracze().size() - 1) && gra.czyPotrzebujeLeczenia(0)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(0));
            } else if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            }
        }

        return null;
    }

    public Czynnosc wybierzDynamit(Gra gra) {
        if (gra.dystansPrawo(gra.getAktualnyGracz(), 0) > 3) {
            int i = gra.getAktualnyGracz();
            HashSet<Gracz> doRozpatrzenia = new HashSet<>();
            HashSet<Gracz> podejrzani;

            while (i != 0) {
                i = gra.nastepnyGracz(i);
                if (i != 0) {
                    doRozpatrzenia.add(gra.getGracze().get(i));
                }
            }

            podejrzani = podejrzani(doRozpatrzenia);

            if (podejrzani.size() * 3 > doRozpatrzenia.size() * 2) {
                return new Czynnosc(Akcja.DYNAMIT, null);
            }
        }

        return null;
    }

    public HashSet<Gracz> podejrzani(HashSet<Gracz> doRozpatrzenia) {
        Iterator<Gracz> iterator = doRozpatrzenia.iterator();
        HashSet<Gracz> wynik = new HashSet<>();

        while (iterator.hasNext()) {
            Gracz rozpatrywany = iterator.next();

            if (rozpatrywany.getLiczbaZabitychPomocnikow() > rozpatrywany.getLiczbaZabitychBandytow()) {
                wynik.add(rozpatrywany);
            }
        }

        return wynik;
    }

    public HashSet<Gracz> wZasiegu(Gra gra) {
        int aktualny = gra.getAktualnyGracz();
        int zasieg = gra.getGracze().get(aktualny).getZasięg();
        HashSet<Gracz> kandydaci = new HashSet<>();
        int rozpatrywanyGracz = aktualny;

        for (int i = 0; i < zasieg; i++) {
            rozpatrywanyGracz = gra.nastepnyGracz(rozpatrywanyGracz);
            if (rozpatrywanyGracz != 0) {
                kandydaci.add(gra.getGracze().get(rozpatrywanyGracz));
            }
        }

        return kandydaci;
    }
}
