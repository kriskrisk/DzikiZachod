package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public class StrategiaSzeryfaZliczajaca extends StrategiaSzeryfa {

    public StrategiaSzeryfaZliczajaca() {}

    public Gracz wybierzCel(Gra gra) {
        ArrayList<Gracz> gracze = gra.gracze();
        HashSet<Gracz> kandydaci = new HashSet<>();

        for (int i = 0; i < gracze.size(); i++) {
            Gracz rozpatrywany = gracze.get(i);
            if (rozpatrywany.liczbaZabitychPomocnikow() > rozpatrywany.liczbaZabitychBandytow()) {
                kandydaci.add(rozpatrywany);
            }
        }

        if (kandydaci.isEmpty()) {
            return null;
        }

        return gra.wybierzZeZbioru(kandydaci);
    }

    public Czynnosc wybierzAkcje(Gra gra) {
        HashSet<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();

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
