package dzikizachod;

import java.util.HashSet;

public class PomocnikSzeryfa extends Gracz {
    public PomocnikSzeryfa() {
        super(new HashSet<Akcja>(), false, new StrategiaPomocnikaSzeryfaDomyslna(), 1);
    }

    public PomocnikSzeryfa(StrategiaPomocnikaSzeryfa strategia) {
        super(new HashSet<Akcja>(), false, strategia, 1);
    }

    public void komunikatOSmierci (int numer) {
        System.out.println("GRACZ " + numer + " (Pomocnik Szeryfa):");
        System.out.println("  MARTWY");
        System.out.println();
    }

    public void komunikatOGraczuPoczatek(int numer) {
        System.out.println("GRACZ " + numer + " (Pomocnik Szeryfa):");
        System.out.print("  Akcje: ");
        wypiszReke();
        System.out.println("  Ruchy:");
    }

    public void wypiszSwojStan(int numer) {
        if (this.czyZyje()) {
            System.out.println("  " + numer + ": Pomocnik Szeryfa (liczba żyć: " + this.obecnaIloscPunktowZycia() + ")");
        } else {
            System.out.println("  " + numer + ": X (Pomocnik Szeryfa)");
        }
    }
}
