package dzikizachod;

import java.util.*;

public class Gra {
    private ArrayList<Gracz> gracze;//szeryf jest zawsze na zerowej pozycji;
    private PulaAkcji pula;
    private int aktualnyGracz;
    private boolean czyJestDynamit;
    private WygranaStrona stanGry;

    public Gra() {
        this.gracze = new ArrayList<>();
        this.pula = new PulaAkcji();
        this.aktualnyGracz = 0;
        this.czyJestDynamit = false;
        this.stanGry = WygranaStrona.GRA_W_TRAKCIE;
    }

    public LinkedList<Akcja> akcjeAktualnegoGracza() {
        return gracze().get(aktualnyGracz).posiadaneAkcje();
    }

    public int nastepnyGracz(int aktualny) {
        int nastepny = aktualny;

        do {
            if (nastepny == gracze.size() - 1) {
                nastepny = 0;
            } else {
                nastepny = nastepny + 1;
            }
        } while (!gracze.get(nastepny).czyZyje());

        return nastepny;
    }

    public int poprzedniGracz(int aktualny) {
        int poprzedni = aktualny;

        do {
            if (poprzedni == 0) {
                poprzedni = gracze.size() - 1;
            } else {
                poprzedni = poprzedni - 1;
            }
        } while (!gracze.get(poprzedni).czyZyje());

        return poprzedni;
    }

    public int rzutKoscia() {
        Random r = new Random();
        int n = r.nextInt(6);

        return n + 1;
    }

    public Gracz wybierzZeZbioru(LinkedList<Gracz> zbior) {
        Random r = new Random();
        int losowa = r.nextInt(zbior.size());

        return zbior.get(losowa);
    }

    public LinkedList<Gracz> zywiGracze (){
        LinkedList<Gracz> zywi = new LinkedList<>();
        for (int i = 0; i < gracze.size(); i++) {
            Gracz rozpatrywany = gracze.get(i);

            if (rozpatrywany.czyZyje()) {
                zywi.add(rozpatrywany);
            }
        }

        return zywi;
    }

    public Gracz wybierzNieSzeryfaZeZbioru(LinkedList<Gracz> zbior) {
        Random r = new Random();
        int losowa = r.nextInt(zbior.size());
        Gracz wynik = zbior.get(losowa);

        while (wynik.getClass() == Szeryf.class) {
            losowa = r.nextInt(zbior.size());
            wynik = zbior.get(losowa);
        }

        return wynik;
    }

    public LinkedList<Gracz> strzeliliDoSzeryfa() {
        LinkedList<Gracz> strzelili = new LinkedList<>();

        for (int i = 0; i < gracze.size(); i++) {
            Gracz rozpatrywany = gracze.get(i);

            if (rozpatrywany.czyZyje() && rozpatrywany.czyStrzelilDoSzeryfa()) {
                strzelili.add(rozpatrywany);
            }
        }

        return strzelili;
    }

    public LinkedList<Gracz> wZasiegu(LinkedList<Gracz> kandydaci) {
        ListIterator<Gracz> iterator = kandydaci.listIterator();
        LinkedList<Gracz> wynik = new LinkedList<>();

        while (iterator.hasNext()) {
            Gracz rozpatrywany = iterator.next();
            if (czyWZasiegu(aktualnyGracz, gracze().indexOf(rozpatrywany))) {
                wynik.add(rozpatrywany);
            }
        }

        return wynik;
    }

    public int dystansPrawo(int strzelec, int cel) {
        int dystansPrawo = 0;
        int pozycja = strzelec;

        while (pozycja != cel) {
            pozycja = nastepnyGracz(pozycja);
            dystansPrawo++;
        }

        return dystansPrawo;
    }

    public int dystansLewo(int strzelec, int cel) {
        int dystansLewo = 0;
        int pozycja = strzelec;

        while (pozycja != cel) {
            pozycja = poprzedniGracz(pozycja);
            dystansLewo++;
        }

        return dystansLewo;
    }

    public boolean czyWZasiegu(int strzelec, int cel) {
        int zasieg = gracze().get(strzelec).zasięg();

        return czyWZasiegu(strzelec, cel, zasieg);
    }

    public boolean czyWZasiegu(int strzelec, int cel, int zasieg) {
        if (strzelec == cel) {
            return false;
        }

        return zasieg >= dystansPrawo(strzelec, cel) || zasieg >= dystansLewo(strzelec, cel);
    }

    public boolean czyPotrzebujeLeczenia(int numerGracza)   {
        return gracze.get(numerGracza).obecnaIloscPunktowZycia() < gracze.get(numerGracza).maxIloscPunktowZycia()
                && gracze().get(numerGracza).czyZyje();
    }

    public int aktualnyGracz() {
        return aktualnyGracz;
    }

    public ArrayList<Gracz> gracze() {
        return gracze;
    }

    public void puscDynamit() {

        this.czyJestDynamit = true;
    }

    public void wypiszGraczy() {
        System.out.println("  Gracze:");

        for (int i = 0; i < gracze().size(); i++) {
            gracze().get(i).wypiszSwojStan(i);
        }

        System.out.println();
    }

    public void sprawdźCzyKoniec() {
        boolean czyZyjeJakisBandyta = false;
        int i = 1;

        while (!czyZyjeJakisBandyta && i < gracze().size()) {
            if (gracze().get(i).getClass().equals(Bandyta.class)) {
                czyZyjeJakisBandyta = true;
            }

            i++;
        }

        if (!gracze().get(0).czyZyje()) {
            stanGry = WygranaStrona.BANDYCI;
        } else if (!czyZyjeJakisBandyta) {
            stanGry = WygranaStrona.SZERYF_I_POMOCNICY;
        }
    }

    public PulaAkcji pula() {
        return pula;
    }

    public void obsluzDynamit() {
        if (czyJestDynamit && rzutKoscia() == 1) {
            if(gracze.get(aktualnyGracz).obecnaIloscPunktowZycia() > 3) {
                int przedDynamitem = gracze.get(aktualnyGracz).obecnaIloscPunktowZycia();
                gracze.get(aktualnyGracz).obecnaIloscPunktowZycia(przedDynamitem - 3);
            } else {
                gracze.get(aktualnyGracz).obecnaIloscPunktowZycia(0);
            }
        }
    }

    public void rozgrywka(ArrayList<Gracz> nowiGracze, PulaAkcji pula) {
        this.pula = pula;
        int i = 0;

        //w "nowiGracze" musi istnieć szeryf
        while (!nowiGracze.get(i).getClass().equals(Szeryf.class)) {
            i++;
        }

        this.gracze.add(nowiGracze.remove(i));

        Random r = new Random();
        int ilu = nowiGracze.size();

        for (int l = 0; l < ilu; l++) {
            int losowa = r.nextInt(nowiGracze.size());
            this.gracze().add(nowiGracze.remove(losowa));
        }

        this.pula.tasuj();

        for (int l = 0; l < gracze().size(); l++) {
            gracze().get(l).dobierzAkcje(this.pula);
        }

        System.out.println("**START");
        wypiszGraczy();

        int numerTury = 1;
        while (this.stanGry.equals(WygranaStrona.GRA_W_TRAKCIE) && numerTury <= 42) {
            System.out.println("** TURA " + numerTury);

            for (aktualnyGracz = 0; aktualnyGracz < gracze.size(); aktualnyGracz++) {
                if (stanGry.equals(WygranaStrona.GRA_W_TRAKCIE)) {
                    if (gracze.get(aktualnyGracz).czyZyje()) {
                        gracze.get(aktualnyGracz).komunikatOGraczuPoczatek(aktualnyGracz);

                        obsluzDynamit();

                        for (int j = 0; j < 5; j++) {
                            gracze.get(aktualnyGracz()).wykonajRuch(this);

                            if (!stanGry.equals(WygranaStrona.GRA_W_TRAKCIE)) {
                                break;
                            }
                        }

                        gracze.get(aktualnyGracz()).dobierzAkcje(pula);
                        wypiszGraczy();
                    } else {
                        gracze.get(aktualnyGracz()).komunikatOSmierci(aktualnyGracz);
                    }
                }
            }

            numerTury++;
        }

        System.out.println("**KONIEC");
        System.out.print("  WYGRANA STRONA: ");

        if (this.stanGry.equals(WygranaStrona.SZERYF_I_POMOCNICY)) {
            System.out.println("szeryf i pomocnicy");
        } else if (this.stanGry.equals(WygranaStrona.BANDYCI)){
            System.out.println("bandyci");
        } else {
            System.out.println("REMIS");
        }
    }
}
