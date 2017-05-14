package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    public StrategiaSzeryfaDomyslna() {}

    public Czynnosc wybierzAkcje(HashSet<Akcja> posiadaneAkcje, ArrayList<Gracz> aktualnyStanGry, int numerObecnegoGracza) {
        Czynnosc ruch = zwiekszanieZasiegu(posiadaneAkcje);

        if (ruch != null) {
            return ruch;
        }

        ruch = leczenie(posiadaneAkcje, aktualnyStanGry, numerObecnegoGracza);

        if (ruch != null) {
            return ruch;
        }


    }

}
