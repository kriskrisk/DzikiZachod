package dzikizachod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

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
        this.stanGry = WygranaStrona.GRA_W_TRAKCI;
    }

    public void aktualnyGracz(int aktualnyGracz) {
        this.aktualnyGracz = aktualnyGracz;
    }

    public void doNastepnegoGracza() {
        if (aktualnyGracz == gracze.size() - 1) {
            aktualnyGracz(0);
        } else {
            aktualnyGracz(aktualnyGracz + 1);
        }
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

            if (rozpatrywany.czyStrzelilDoSzeryfa()) {
                strzelili.add(rozpatrywany);
            }
        }

        return strzelili;
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

    public boolean isCzyJestDynamit() {
        return czyJestDynamit;
    }

    public void czyJestDynamit(boolean czyJestDynamit) {

        this.czyJestDynamit = czyJestDynamit;
    }

    public void wypiszGraczy() {
        System.out.println("Gracze:");

        for (int i = 0; i < gracze().size(); i++) {
            gracze().get(i).wypiszSwojStan(i);
        }

        System.out.println();
    }

    public void sprawdźCzyKoniec() {
        boolean czyZyjeJakisBandyta = false;
        int i = 0;

        while (!czyZyjeJakisBandyta && i < gracze().size()) {
            if (gracze().get(i).getClass().equals(Bandyta.class)) {
                czyZyjeJakisBandyta = true;
            }
        }

        if (!gracze().get(0).czyZyje()) {
            stanGry = WygranaStrona.BANDYCI;
        } else if (!czyZyjeJakisBandyta) {
            stanGry = WygranaStrona.SZERYF_I_POMOCNICY;
        }
    }

    public void rozgrywka(ArrayList<Gracz> nowiGracze, PulaAkcji pula) {
        this.pula = pula;
        int i = 0;

        //w "nowiGracze" musi istnieć szeryf
        while (!nowiGracze.get(i).getClass().equals(Szeryf.class)) {
            i++;
        }

        this.gracze.add(nowiGracze.get(i));

        //losowanie kolejności

        System.out.println("**START");
        wypiszGraczy();

        int numerTury = 1;
        while (this.stanGry.equals(WygranaStrona.GRA_W_TRAKCI) && numerTury <= 42) {
            System.out.println("** TURA " + numerTury);

            for (aktualnyGracz = 0; aktualnyGracz < gracze.size(); aktualnyGracz++) {
                if (stanGry.equals(WygranaStrona.GRA_W_TRAKCI)) {
                    if (gracze.get(aktualnyGracz).czyZyje()) {

                        gracze.get(aktualnyGracz()).komunikatOGraczuPoczatek(aktualnyGracz);
                        for (int j = 0; j < 5; j++) {
                            gracze.get(aktualnyGracz()).wykonajRuch(this);
                        }
                    } else {
                        gracze.get(aktualnyGracz()).komunikatOSmierci(aktualnyGracz);
                    }
                }
            }

            System.out.println();
            this.wypiszGraczy();
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
