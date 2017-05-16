package dzikizachod;

import java.util.HashSet;

public class Szeryf extends Gracz{
    public Szeryf() {
        super(new HashSet<Akcja>(), true, new StrategiaSzeryfaDomyslna(), 1);
    }

    public Szeryf(StrategiaSzeryfa strategia) {
        super(new HashSet<Akcja>(), true, strategia, 1);
    }
}
