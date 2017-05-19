package dzikizachod;

import java.util.ArrayList;
import java.util.LinkedList;

public class StrategiaSzeryfaZliczajaca extends StrategiaSzeryfa {

    public StrategiaSzeryfaZliczajaca() {}

    //Zwraca listę graczy którzy są w zasięgu i zabili więcej pomocników szeryfa niż bandytów.
    public LinkedList<Gracz> wybierzCel(Gra gra) {
        ArrayList<Gracz> gracze = gra.gracze();
        LinkedList<Gracz> kandydaci = new LinkedList<>();

        for (int i = 0; i < gracze.size(); i++) {
            Gracz rozpatrywany = gracze.get(i);

            if (rozpatrywany.liczbaZabitychPomocnikow() > rozpatrywany.liczbaZabitychBandytow()) {
                kandydaci.add(rozpatrywany);
            }
        }

        return gra.wZasiegu(kandydaci);
    }

    public Czynnosc wybierzAkcje(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();

        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            LinkedList<Gracz> cele = gra.wZasiegu(gra.strzeliliDoSzeryfa());

            if (!cele.isEmpty()) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(cele));
            } else if (wybierzCel(gra).size() != 0) {
                return new Czynnosc(Akcja.STRZEL, gra.wybierzZeZbioru(wybierzCel(gra)));
            }
        }

        return new Czynnosc(Akcja.BRAK, null);
    }
}
