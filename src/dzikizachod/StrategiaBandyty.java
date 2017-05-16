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
}
