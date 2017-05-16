package dzikizachod;

import java.util.HashSet;

public class PomocnikSzeryfa extends Gracz {
    public PomocnikSzeryfa() {
        super(new HashSet<Akcja>(), false, new StrategiaPomocnikaSzeryfaDomyslna(), 1);
    }

    public PomocnikSzeryfa(StrategiaPomocnikaSzeryfa strategia) {
        super(new HashSet<Akcja>(), false, strategia, 1);
    }
}
