package dzikizachod;

import java.util.HashSet;

public class Szeryf extends Gracz{
    public Szeryf() {
        super(new HashSet<Akcja>(), true, new StrategiaSzeryfaDomyslna(), 1);
    }

    public Szeryf(StrategiaSzeryfa strategia) {
        super(new HashSet<Akcja>(), true, strategia, 1);
    }

    public void komunikatOSmierci (int numer) {
        System.out.println("GRACZ " + numer + " (Szeryf):");
        System.out.println("  MARTWY");
        System.out.println();
    }

    public void komunikatOGraczuPoczatek(int numer) {
        System.out.println("GRACZ " + numer + " (Szeryf):");
        System.out.print("  Akcje: ");
        wypiszReke();
        System.out.println("  Ruchy:");
    }

    public void wypiszSwojStan(int numer) {
        if (this.czyZyje()) {
            System.out.println("  " + numer + ": Szeryf (liczba żyć: " + this.obecnaIloscPunktowZycia() + ")");
        } else {
            System.out.println("  " + numer + ": X (Szeryf)");
        }
    }
}
