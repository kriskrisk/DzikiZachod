package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class StrategiaPomocnikaSzeryfa extends Strategia {
    public Czynnosc leczenie(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();
        ArrayList<Gracz> aktualnyStanGry = gra.gracze();
        int numerObecnegoGracza = gra.aktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if ((numerObecnegoGracza == 1 || numerObecnegoGracza == gra.gracze().size() - 1) && gra.czyPotrzebujeLeczenia(0)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(0));
            } else if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            }
        }

        return null;
    }

    public Czynnosc wybierzDynamit(Gra gra) {
        if (gra.dystansPrawo(gra.aktualnyGracz(), 0) > 3) {
            int i = gra.aktualnyGracz();
            HashSet<Gracz> doRozpatrzenia = new HashSet<>();
            HashSet<Gracz> podejrzani;

            while (i != 0) {
                i = gra.nastepnyGracz(i);
                if (i != 0) {
                    doRozpatrzenia.add(gra.gracze().get(i));
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

            if (rozpatrywany.liczbaZabitychPomocnikow() > rozpatrywany.liczbaZabitychBandytow()) {
                wynik.add(rozpatrywany);
            }
        }

        return wynik;
    }

    public HashSet<Gracz> wZasiegu(Gra gra) {
        int aktualny = gra.aktualnyGracz();
        int zasieg = gra.gracze().get(aktualny).zasięg();
        HashSet<Gracz> kandydaci = new HashSet<>();
        int rozpatrywanyGracz = aktualny;

        for (int i = 0; i < zasieg; i++) {
            rozpatrywanyGracz = gra.nastepnyGracz(rozpatrywanyGracz);
            if (rozpatrywanyGracz != 0) {
                kandydaci.add(gra.gracze().get(rozpatrywanyGracz));
            }
        }

        return kandydaci;
    }
}
