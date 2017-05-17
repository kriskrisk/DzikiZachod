package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class StrategiaBandyty extends Strategia {

    public Czynnosc leczenie(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();
        ArrayList<Gracz> aktualnyStanGry = gra.gracze();
        int numerObecnegoGracza = gra.aktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            }
        }

        return null;
    }

    public Czynnosc wybierzDynamit(Gra gra) {
        if (gra.dystansPrawo(gra.aktualnyGracz(), 0) < 3) {
            return new Czynnosc(Akcja.DYNAMIT, null);
        }

        return null;
    }

    public Czynnosc strzelDoszeryfa(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            int aktualny = gra.aktualnyGracz();
            int lewoDystans = gra.dystansLewo(aktualny, 0);
            int prawoDystans = gra.dystansPrawo(aktualny, 0);
            int zasieg = gra.gracze().get(aktualny).zasięg();

            if (lewoDystans <= zasieg || prawoDystans <= zasieg) {
                return new Czynnosc(Akcja.STRZEL, gra.gracze().get(0));
            }
        }

        return null;
    }
}
