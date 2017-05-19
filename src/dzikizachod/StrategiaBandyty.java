package dzikizachod;

import java.util.LinkedList;

public abstract class StrategiaBandyty extends Strategia {
    //Decyduje czy wybrać dynamit jako akcję do wykonania.
    public Czynnosc wybierzDynamit(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.akcjeAktualnegoGracza();
        if (posiadaneAkcje.contains(Akcja.DYNAMIT) && gra.dystansPrawo(gra.aktualnyGracz(), 0) < 3) {
            return new Czynnosc(Akcja.DYNAMIT, null);
        }

        return new Czynnosc(Akcja.BRAK, null);
    }

    public Czynnosc strzelDoszeryfa(Gra gra) {
        LinkedList<Akcja> posiadaneAkcje = gra.gracze().get(gra.aktualnyGracz()).posiadaneAkcje();

        if (posiadaneAkcje.contains(Akcja.STRZEL)) {
            int aktualny = gra.aktualnyGracz();
            int lewoDystans = gra.dystansLewo(aktualny, 0);
            int prawoDystans = gra.dystansPrawo(aktualny, 0);
            int zasieg = gra.gracze().get(aktualny).zasięg();

            if (lewoDystans <= zasieg || prawoDystans <= zasieg) {
                return new Czynnosc(Akcja.STRZEL, gra.gracze().get(0));
            }
        }

        return new Czynnosc(Akcja.BRAK, null);
    }
}
