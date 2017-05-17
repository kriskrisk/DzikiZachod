package dzikizachod;

public class Czynnosc {
    private Akcja akcja;
    private Gracz osoba;

    public Czynnosc(Akcja akcja, Gracz osoba) {
        this.akcja = akcja;
        this.osoba = osoba;
    }

    public Akcja akcja() {
        return akcja;
    }

    public Gracz osoba() {
        return osoba;
    }
}
