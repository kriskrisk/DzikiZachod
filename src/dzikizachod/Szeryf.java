package dzikizachod;

import java.util.HashSet;
import java.util.LinkedList;

public class Szeryf extends Gracz{
    public Szeryf() {
        super(new LinkedList<>(), true, new StrategiaSzeryfaDomyslna(), 1);
    }

    public Szeryf(StrategiaSzeryfa strategia) {
        super(new LinkedList<>(), true, strategia, 1);
    }

    public void komunikatOSmierci (int numer) {
        System.out.println("  GRACZ " + (numer + 1) + " (Szeryf):");
        System.out.println("    MARTWY");
        System.out.println();
    }

    public void komunikatOGraczuPoczatek(int numer) {
        System.out.println("  GRACZ " + (numer + 1) + " (Szeryf):");
        System.out.print("    Akcje: ");
        wypiszReke();
        System.out.println("    Ruchy:");
    }

    public void wypiszSwojStan(int numer) {
        if (this.czyZyje()) {
            System.out.println("    " + (numer + 1) + ": Szeryf (liczba żyć: " + this.obecnaIloscPunktowZycia() + ")");
        } else {
            System.out.println("    " + (numer + 1) + ": X (Szeryf)");
        }
    }
}
