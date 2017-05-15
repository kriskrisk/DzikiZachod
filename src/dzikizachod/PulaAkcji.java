package dzikizachod;

import java.util.LinkedList;
import java.util.Random;

public class PulaAkcji {
    private LinkedList<Akcja> pula;
    private LinkedList<Akcja> zuzyte;

    public PulaAkcji() {
        this.pula = new LinkedList<Akcja>();
    }

    public void dodaj(Akcja typ, int ilosc) {
        for (int i = 0; i < ilosc; i++) {
            zuzyte.add(typ);
        }
    }

    public void tasuj() {
        pula.clear();
        int ilosc = zuzyte.size();
        Random r = new Random();
        int numerLosowejAkcji;

        for (int i = 0; i < ilosc; i++) {
            numerLosowejAkcji = r.nextInt(ilosc);
            pula.add(zuzyte.get(numerLosowejAkcji));
            zuzyte.remove(numerLosowejAkcji);
            ilosc--;
        }
    }
}

