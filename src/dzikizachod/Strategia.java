package dzikizachod;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Strategia {
    public Czynnosc zwiekszanieZasiegu(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();

        if (posiadaneAkcje.contains(Akcja.ZASIEG_PLUS_DWA)) {
            return new Czynnosc(Akcja.ZASIEG_PLUS_DWA, null);
        } else if (posiadaneAkcje.contains(Akcja.ZASIEG_PLUS_JEDEN)) {
            return new Czynnosc(Akcja.ZASIEG_PLUS_JEDEN, null);
        } else {
            return new Czynnosc(Akcja.BRAK, null);
        }
    }

    public Czynnosc leczenie(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();
        ArrayList<Gracz> aktualnyStanGry = gra.gracze();
        int numerObecnegoGracza = gra.aktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            }
        }

        return new Czynnosc(Akcja.BRAK, null);
    }

    //Jeśli to możliwe wybiera zwiększanie zasięgu lub leczenie
    //jako akcję do wykonania.
    public Czynnosc wybierzLeczenieLubZasieg(Gra gra) {
        Czynnosc ruch = leczenie(gra);

        if (!ruch.akcja().equals(Akcja.BRAK)) {
            return ruch;
        }

        ruch = zwiekszanieZasiegu(gra);

        if (!ruch.akcja().equals(Akcja.BRAK)) {
            return ruch;
        }

        return new Czynnosc(Akcja.BRAK, null);
    }

    public abstract Czynnosc wybierzAkcje(Gra gra);
}
