package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Gra {
    private ArrayList<Gracz> gracze;
    private HashSet<TypZliczeniowy> StrzeliliDoSzeryfa;
    private HashSet<TypZliczeniowy> ZabiliPomocnikaSzeryfa;
    private HashSet<TypZliczeniowy> ZabiliBandyte;
    private int aktualnyGracz;

    public Gra(ArrayList<Gracz> gracze) {

    }

    public int nastepnyGraczPom(int aktualny) {
        if (aktualny == gracze.size() - 1) {
            return 0;
        }

        return aktualny + 1;
    }

    public int nastepnyGracz() {
        int wynik = nastepnyGraczPom(aktualnyGracz);

        while (gracze.get(wynik) == null) {
            wynik = nastepnyGraczPom(wynik);
        }

        return wynik;
    }

    public int poprzedniGraczPom(int aktualny) {
        if (aktualny == 0) {
            return gracze.size() - 1;
        }

        return aktualny - 1;
    }

    public int poprzedniGracz() {
        int wynik = poprzedniGraczPom(aktualnyGracz);

        while (gracze.get(wynik) == null) {
            wynik = poprzedniGraczPom(wynik);
        }

        return wynik;
    }

    public boolean rzutMoneta() {
        Random r = new Random();
        int n = r.nextInt(2);

        return n == 1;
    }

    public Gracz wybierzZeZbioru(HashSet<TypZliczeniowy> zbior) {
        Random r = new Random();
        int losowa = r.nextInt(zbior.size());
        Iterator<TypZliczeniowy> iterator = zbior.iterator();
        TypZliczeniowy wynik = iterator.next();

        for(int i = 0; i < losowa; i++) {
            wynik = iterator.next();
        }

        return wynik.getGracz();
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

    public boolean czyPotrzebujeLeczenia(int numerGracza) {
        return gracze.get(numerGracza).getObecnaIloscPunktowZycia() < gracze.get(numerGracza).getMaxIloscPunktowZycia();
    }

    public HashSet<TypZliczeniowy> getStrzeliliDoSzeryfa() {
        return StrzeliliDoSzeryfa;
    }

    public HashSet<TypZliczeniowy> getZabiliPomocnikaSzeryfa() {
        return ZabiliPomocnikaSzeryfa;
    }

    public HashSet<TypZliczeniowy> getZabiliBandyte() {
        return ZabiliBandyte;
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
