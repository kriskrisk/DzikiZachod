package dzikizachod;

import java.util.HashSet;

public class Bandyta extends Gracz {

    public Bandyta() {
        super(new HashSet<Akcja>(), false, new StrategiaBandytyDomyslna(), 1);
    }

    public Bandyta(StrategiaBandyty strategia) {
        super(new HashSet<Akcja>(), false, strategia, 1);
    }
}
