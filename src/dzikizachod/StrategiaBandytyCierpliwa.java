package dzikizachod;

import java.util.HashSet;

public class StrategiaBandytyCierpliwa extends StrategiaBandyty{
    public StrategiaBandytyCierpliwa() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        if (wybierzLeczenieLubZasieg(gra) != null) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (wybierzDynamit(gra) != null) {
            return wybierzDynamit(gra);
        }

        return strzelDoszeryfa(gra);
    }
}
