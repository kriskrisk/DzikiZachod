package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class StrategiaBandyty extends Strategia {

    public Czynnosc leczenie(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();
        ArrayList<Gracz> aktualnyStanGry = gra.getGracze();
        int numerObecnegoGracza = gra.getAktualnyGracz();

        if (posiadaneAkcje.contains(Akcja.ULECZ)) {
            if (gra.czyPotrzebujeLeczenia(numerObecnegoGracza)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(numerObecnegoGracza));
            } else if (gra.czyPotrzebujeLeczenia(gra.nastepnyGracz()) && gra.getGracze().get(gra.nastepnyGracz()).getClass().equals(Bandyta.class)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(gra.nastepnyGracz()));
            }
            else if (gra.czyPotrzebujeLeczenia(gra.poprzedniGracz()) && gra.getGracze().get(gra.poprzedniGracz()).getClass().equals(Bandyta.class)) {
                return new Czynnosc(Akcja.ULECZ, aktualnyStanGry.get(gra.poprzedniGracz()));
            }
        }

        return null;
    }

    public Czynnosc wybierzDynamit(Gra gra) {
        if (gra.dystansPrawo(gra.getAktualnyGracz(), 0) < 3) {
            return new Czynnosc(Akcja.DYNAMIT, null);
        }

        return null;
    }

    public Czynnosc strzelDoszeryfa(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            int aktualny = gra.getAktualnyGracz();
            int lewoDystans = gra.dystansLewo(aktualny, 0);
            int prawoDystans = gra.dystansPrawo(aktualny, 0);
            int zasieg = gra.getGracze().get(aktualny).getZasięg();

            if (lewoDystans <= zasieg || prawoDystans <= zasieg) {
                return new Czynnosc(Akcja.STRZEL, gra.getGracze().get(0));
            }
        }

        return null;
    }
}
