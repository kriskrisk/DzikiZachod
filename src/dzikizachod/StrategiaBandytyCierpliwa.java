package dzikizachod;

public class StrategiaBandytyCierpliwa extends StrategiaBandyty{
    public StrategiaBandytyCierpliwa() {}

    public Czynnosc wybierzAkcje(Gra gra) {
        if (!wybierzLeczenieLubZasieg(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzLeczenieLubZasieg(gra);
        }

        if (!wybierzDynamit(gra).akcja().equals(Akcja.BRAK)) {
            return wybierzDynamit(gra);
        }

        return strzelDoszeryfa(gra);
    }
}
