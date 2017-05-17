package dzikizachod;

import java.util.HashSet;

public class Bandyta extends Gracz {

    public Bandyta() {
        super(new HashSet<Akcja>(), false, new StrategiaBandytyDomyslna(), 1);
    }

    public Bandyta(StrategiaBandyty strategia) {
        super(new HashSet<Akcja>(), false, strategia, 1);
    }

    public void komunikatOSmierci (int numer) {
        System.out.println("GRACZ " + numer + " (Bandyta):");
        System.out.println("  MARTWY");
        System.out.println();
    }

    public void komunikatOGraczuPoczatek(int numer) {
        System.out.println("GRACZ " + numer + " (Bandyta):");
        System.out.print("  Akcje: ");
        wypiszReke();
        System.out.println("  Ruchy:");
    }

    public void wypiszSwojStan(int numer) {
        if (this.czyZyje()) {
            System.out.println("  " + numer + ": Bandyta (liczba żyć: " + this.obecnaIloscPunktowZycia() + ")");
        } else {
            System.out.println("  " + numer + ": X (Bandyta)");
        }
    }
}
