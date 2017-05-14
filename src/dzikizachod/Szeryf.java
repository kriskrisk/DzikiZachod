package dzikizachod;

import java.util.ArrayList;

public class Szeryf extends Gracz{
    public Szeryf() {
        super(new ArrayList<Akcja>(), 5, 5, new StrategiaSzeryfaDomyslna(), 1);
    }

    public Szeryf(StrategiaSzeryfa strategia) {
        super(new ArrayList<Akcja>(), 5, 5, strategia, 1);
    }
}
