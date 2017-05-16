package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public class StrategiaSzeryfaZliczajaca extends StrategiaSzeryfa {

    public StrategiaSzeryfaZliczajaca() {}

    public Gracz wybierzCel(Gra gra) {
        ArrayList<Gracz> gracze = gra.getGracze();
        HashSet<Gracz> kandydaci = new HashSet<>();

        for (int i = 0; i < gracze.size(); i++) {
            Gracz rozpatrywany = gracze.get(i);
            if (rozpatrywany.getLiczbaZabitychPomocnikow() > rozpatrywany.getLiczbaZabitychBandytow()) {
                kandydaci.add(rozpatrywany);
            }
        }

        if (kandydaci.isEmpty()) {
            return null;
        }

        return gra.wybierzZeZbioru(kandydaci);
    }

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.getGracze().get(gra.getAktualnyGracz()).getPosiadaneAkcje();

        if (wybierzLeczenieLubZasieg(gra) != null) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            if (!gra.strzeliliDoSzeryfa().isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(gra.strzeliliDoSzeryfa()));
            } else {
                return new Czynnosc(Akcja.STRZEL, wybierzCel(gra));
            }
        }

        return null;
    }
}
