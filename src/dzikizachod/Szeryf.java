package dzikizachod;

import java.util.HashSet;

public class Szeryf extends Gracz{
    public Szeryf() {
        super(new HashSet<Akcja>(), 5, 5, new StrategiaSzeryfaDomyslna(), 1);
    }

    public Szeryf(StrategiaSzeryfa strategia) {
        super(new HashSet<>(), 5, 5, strategia, 1);
    }
}
