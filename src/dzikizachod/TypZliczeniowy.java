package dzikizachod;

public class TypZliczeniowy {
    private Gracz gracz;
    private int ileRazy;

    public TypZliczeniowy(Gracz gracz, int ileRazy) {

        this.gracz = gracz;
        this.ileRazy = ileRazy;
    }

    public Gracz getGracz() {
        return gracz;
    }

    public int getIleRazy() {
        return ileRazy;
    }
}
