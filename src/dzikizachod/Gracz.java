package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public abstract class Gracz {
    private HashSet<Akcja> posiadaneAkcje;
    private int maxIloscPunktowZycia;
    private int obecnaIloscPunktowZycia;
    private Strategia strategia;
    private int zasięg;
    private int numer;
    private int liczbaZabitychPomocnikow;
    private int liczbaZabitychBandytow;
    private boolean czyStrzelilDoSzeryfa;

    public Gracz(HashSet<Akcja> posiadaneAkcje, boolean czySzeryf, Strategia strategia, int zasięg) {
        this.posiadaneAkcje = posiadaneAkcje;

        if (!czySzeryf) {
            int IloscPunktowZycia = losujZycie();

            this.maxIloscPunktowZycia = IloscPunktowZycia;
            this.obecnaIloscPunktowZycia = IloscPunktowZycia;
        } else {
            this.maxIloscPunktowZycia = 5;
            this.obecnaIloscPunktowZycia = 5;
        }

        this.strategia = strategia;
        this.zasięg = zasięg;
        this.numer = 0;
        this.liczbaZabitychPomocnikow = 0;
        this.liczbaZabitychBandytow = 0;
        this.czyStrzelilDoSzeryfa = false;
    }

    private int losujZycie() {
        Random r = new Random();
        int losowa = r.nextInt(2);

        return 3 + losowa;
    }

    public int getLiczbaZabitychPomocnikow() {
        return liczbaZabitychPomocnikow;
    }

    public int getLiczbaZabitychBandytow() {
        return liczbaZabitychBandytow;
    }

    public boolean getCzyStrzelilDoSzeryfa() {
        return czyStrzelilDoSzeryfa;
    }

    public int getNumer() {
        return numer;
    }

    public HashSet<Akcja> getPosiadaneAkcje() {
        return posiadaneAkcje;
    }

    public int getMaxIloscPunktowZycia() {
        return maxIloscPunktowZycia;
    }

    public int getObecnaIloscPunktowZycia() {
        return obecnaIloscPunktowZycia;
    }

    public void setObecnaIloscPunktowZycia(int obecnaIloscPunktowZycia) {
        this.obecnaIloscPunktowZycia = obecnaIloscPunktowZycia;
    }

    public ArrayList<Akcja> dobierzAkcje(PulaAkcji pula) {
        //dopełnia akcje do 5-ciu
    }

    public void strzel(Gracz cel) {
        //strzela do innego gracza
    }

    //założenie: musi dać się uleczyć (obecna < max)
    public void ulecz(Gracz ranny) {
        int obecna = ranny.getObecnaIloscPunktowZycia();
        ranny.setObecnaIloscPunktowZycia(obecna + 1);
    }

    public void zwiększZasięg() {
        this.zasięg++;
    }

    public void rzucDynamit() {

    }

    public Akcja wybierzAkcję() {
        //wybiera akcję z ręki;
    }

    public boolean czyZyje() {
        //sprawdza czy życie != 0
    }
}
