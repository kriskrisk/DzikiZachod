package dzikizachod;

import java.util.LinkedList;

public class Bandyta extends Gracz {

    public Bandyta() {
        super(new LinkedList<>(), false, new StrategiaBandytyDomyslna(), 1);
    }

    public Bandyta(StrategiaBandyty strategia) {
        super(new LinkedList<>(), false, strategia, 1);
    }

    public void komunikatOSmierci (int numer) {
        System.out.println("  GRACZ " + (numer + 1) + " (Bandyta):");
        System.out.println("    MARTWY");
        System.out.println();
    }

    public void komunikatOGraczuPoczatek(int numer) {
        System.out.println("  GRACZ " + (numer + 1) + " (Bandyta):");
        System.out.print("    Akcje: ");
        wypiszReke();
        System.out.println("    Ruchy:");
    }

    public void wypiszSwojStan(int numer) {
        if (this.czyZyje()) {
            System.out.println("    " + (numer + 1) + ": Bandyta (liczba żyć: " + this.obecnaIloscPunktowZycia() + ")");
        } else {
            System.out.println("    " + (numer + 1) + ": X (Bandyta)");
        }
    }
}
