package dzikizachod;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public abstract class Gracz {
    private HashSet<Akcja> posiadaneAkcje;
    private int maxIloscPunktowZycia;
    private int obecnaIloscPunktowZycia;
    private Strategia strategia;
    private int zasięg;
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
        this.liczbaZabitychPomocnikow = 0;
        this.liczbaZabitychBandytow = 0;
        this.czyStrzelilDoSzeryfa = false;
    }

    private int losujZycie() {
        Random r = new Random();
        int losowa = r.nextInt(2);

        return 3 + losowa;
    }

    public int liczbaZabitychPomocnikow() {
        return liczbaZabitychPomocnikow;
    }

    public int liczbaZabitychBandytow() {
        return liczbaZabitychBandytow;
    }

    public boolean czyStrzelilDoSzeryfa() {
        return czyStrzelilDoSzeryfa;
    }

    public HashSet<Akcja> posiadaneAkcje() {
        return posiadaneAkcje;
    }

    public int maxIloscPunktowZycia() {
        return maxIloscPunktowZycia;
    }

    public int obecnaIloscPunktowZycia() {
        return obecnaIloscPunktowZycia;
    }

    public void obecnaIloscPunktowZycia(int obecnaIloscPunktowZycia) {
        this.obecnaIloscPunktowZycia = obecnaIloscPunktowZycia;
    }

    public int zasięg() {
        return zasięg;
    }

    public void dobierzAkcje(PulaAkcji pula) {
        while (posiadaneAkcje.size() < 5) {
            posiadaneAkcje.add(pula.pula().removeFirst());
        }
    }

    public void strzel(Gracz cel) {
        int obecna = cel.obecnaIloscPunktowZycia();
        cel.obecnaIloscPunktowZycia(obecna - 1);
    }

    //założenie: musi dać się uleczyć (obecna < max)
    public void ulecz(Gracz ranny) {
        int obecna = ranny.obecnaIloscPunktowZycia();
        ranny.obecnaIloscPunktowZycia(obecna + 1);
    }

    public void zwiększZasięgOJeden() {
        this.zasięg++;
    }

    public void zwiększZasięgODwa() {
        zasięg = zasięg + 2;
    }

    public abstract void komunikatOSmierci (int numer);

    public void wypiszReke() {
        System.out.print("[");
        Iterator<Akcja> reka = posiadaneAkcje.iterator();

        for (int i = 0; i < 4; i++) {
            System.out.print(reka.next().toString() + ", ");
        }

        System.out.println(reka.next().toString() + "]");
    }

    public abstract void komunikatOGraczuPoczatek(int numer);

    public abstract void wypiszSwojStan(int numer);

    public void wykonajRuch(Gra gra) {
        Czynnosc ruch = strategia.wybierzAkcje(gra);
        Akcja akcja = ruch.akcja();

        if (ruch != null) {
            if (akcja.equals(Akcja.ULECZ)) {
                ulecz(ruch.osoba());
                int indeksUleczonego = gra.gracze().indexOf(ruch.osoba());

                if (indeksUleczonego == 0) {
                    System.out.println("ULECZ " + indeksUleczonego);
                } else {
                    System.out.println("ULECZ");
                }
                System.out.println("ULECZ " + gra.gracze().indexOf(ruch.osoba()));
            } else if (akcja.equals(Akcja.ZASIEG_PLUS_JEDEN)) {
                zwiększZasięgOJeden();
                System.out.println("ZASIEG_PLUS_JEDEN");
            } else if (akcja.equals(Akcja.ZASIEG_PLUS_DWA)) {
                zwiększZasięgODwa();
                System.out.println("ZASIEG_PLUS_DWA");
            } else if (akcja.equals(Akcja.DYNAMIT)) {
                gra.czyJestDynamit(true);
                System.out.println("DYNAMIT");
            } else if (akcja.equals(Akcja.STRZEL)) {
                strzel(ruch.osoba());
                System.out.println("STRZEL " + gra.gracze().indexOf(ruch.osoba()));
                gra.sprawdźCzyKoniec();
            }
        }
    }

    public boolean czyZyje() {
        return obecnaIloscPunktowZycia > 0;
    }
}
