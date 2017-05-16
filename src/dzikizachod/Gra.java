package dzikizachod;

import javax.management.DynamicMBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Gra {
    private ArrayList<Gracz> gracze;
    //private HashSet<TypZliczeniowy> StrzeliliDoSzeryfa;
    //private HashSet<TypZliczeniowy> ZabiliPomocnikaSzeryfa;
    //private HashSet<TypZliczeniowy> ZabiliBandyte;
    private int aktualnyGracz;

    public Gra(ArrayList<Gracz> gracze) {

    }

    public int nastepnyGracz() {
        if (aktualnyGracz == gracze.size() - 1) {
            return 0;
        }

        return aktualnyGracz + 1;
    }

    public int nastepnyGracz(int aktualny) {
        if (aktualny == gracze.size() - 1) {
            return 0;
        }

        return aktualny + 1;
    }

    public int poprzedniGracz() {
        if (aktualnyGracz == 0) {
            return gracze.size() - 1;
        }

        return aktualnyGracz - 1;
    }

    public int poprzedniGracz(int aktualny) {
        if (aktualny == 0) {
            return gracze.size() - 1;
        }

        return aktualny - 1;
    }

    public boolean rzutMoneta() {
        Random r = new Random();
        int n = r.nextInt(2);

        return n == 1;
    }

    public Gracz wybierzZeZbioru(HashSet<Gracz> zbior) {
        Random r = new Random();
        int losowa = r.nextInt(zbior.size());
        Iterator<Gracz> iterator = zbior.iterator();
        Gracz wynik = iterator.next();

        for(int i = 0; i < losowa; i++) {
            wynik = iterator.next();
        }

        return wynik;
    }

    public Gracz wybierzNieSzeryfaZeZbioru(ArrayList<Gracz> zbior) {
        Random r = new Random();
        int losowa = r.nextInt(zbior.size());
        Gracz wynik = zbior.get(losowa);

        while (wynik.getClass() == Szeryf.class) {
            losowa = r.nextInt(zbior.size());
            wynik = zbior.get(losowa);
        }

        return wynik;
    }

    public HashSet<Gracz> strzeliliDoSzeryfa() {
        HashSet<Gracz> strzelili = new HashSet<>();

        for (int i = 0; i < gracze.size(); i++) {
            Gracz rozpatrywany = gracze.get(i);

            if (rozpatrywany.getCzyStrzelilDoSzeryfa()) {
                strzelili.add(rozpatrywany);
            }
        }

        return strzelili;
    }

    public int dystans(int strzelec, int cel) {
        int dystansPrawo = 0;
        int dystansLewo = 0;
        int pozycja = aktualnyGracz;

        while (pozycja != cel) {
            pozycja = nastepnyGracz(pozycja);
            dystansPrawo++;
        }

        pozycja = aktualnyGracz;

        while (pozycja != cel) {
            pozycja = poprzedniGracz(pozycja);
            dystansLewo++;
        }

        if (dystansLewo < dystansPrawo) {
            return dystansLewo;
        }

        return dystansPrawo;
    }

    public boolean czyPotrzebujeLeczenia(int numerGracza) {
        return gracze.get(numerGracza).getObecnaIloscPunktowZycia() < gracze.get(numerGracza).getMaxIloscPunktowZycia();
    }

    public int getAktualnyGracz() {
        return aktualnyGracz;
    }

    public ArrayList<Gracz> getGracze() {
        return gracze;
    }

    public int graczZaNTur(int n) {
        int wynik = aktualnyGracz + n;

        while (wynik >= gracze.size()) {
            wynik = wynik - gracze.size();
        }

        return wynik;
    }

    public void rozgrywka(ArrayList<Gracz> gracze, PulaAkcji pula) {
        //na początku losowanie kolejniści

    }
}
