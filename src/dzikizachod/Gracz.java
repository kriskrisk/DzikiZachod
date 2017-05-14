package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Gracz {
    private HashSet<Akcja> posiadaneAkcje;//nie wem jeszcze jaka kolekcja
    private int maxIloscPunktowZycia;
    private int obecnaIloscPunktowZycia;
    private Strategia strategia;
    private int zasięg;

    public Gracz(HashSet<Akcja> posiadaneAkcje, int maxIloscPunktowZycia, int obecnaIloscPunktowZycia, Strategia strategia, int zasięg) {
        this.posiadaneAkcje = posiadaneAkcje;
        this.maxIloscPunktowZycia = maxIloscPunktowZycia;
        this.obecnaIloscPunktowZycia = obecnaIloscPunktowZycia;
        this.strategia = strategia;
        this.zasięg = zasięg;
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
