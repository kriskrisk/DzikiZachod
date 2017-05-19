package dzikizachod;

import java.util.*;

public abstract class StrategiaPomocnikaSzeryfa extends Strategia {
    @Override
    public Czynnosc leczenie(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();
        ArrayList<Gracz> aktualnyStanGry = gra.gracze();
        int numerObecnegoGracza = gra.aktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if (gra.czyWZasiegu(numerObecnegoGracza, 0, 1) && gra.czyPotrzebujeLeczenia(0)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(0));
            } else if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            }
        }

        return new Czynnosc(Akcja.BRAK, null);
    }

    //Decyduje czy wybrać dynamit jako akcję do wykonania.
    public Czynnosc wybierzDynamit(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();
        if (posiadaneAkcje.contains(Akcja.DYNAMIT) && gra.dystansPrawo(gra.aktualnyGracz(), 0) > 3) {

            int i = gra.aktualnyGracz();
            LinkedList<Gracz> doRozpatrzenia = new LinkedList<>();
            LinkedList<Gracz> podejrzani;

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

        return new Czynnosc(Akcja.BRAK, null);
    }

    //Zwraca listę graczy którzy zabili więcej pomocników szeryfa niż bandytów.
    public LinkedList<Gracz> podejrzani(LinkedList<Gracz> doRozpatrzenia) {
        ListIterator<Gracz> iterator = doRozpatrzenia.listIterator();
        LinkedList<Gracz> wynik = new LinkedList<>();

        while (iterator.hasNext()) {
            Gracz rozpatrywany = iterator.next();

            if (rozpatrywany.liczbaZabitychPomocnikow() > rozpatrywany.liczbaZabitychBandytow()) {
                wynik.add(rozpatrywany);
            }
        }

        return wynik;
    }
}
