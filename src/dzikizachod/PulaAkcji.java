package dzikizachod;

import java.util.LinkedList;

public class PulaAkcji {
    private LinkedList<Akcja> pula;
    private LinkedList<Akcja> zużyte;

    public PulaAkcji() {
        this.pula = new LinkedList<Akcja>();
    }

    /*
    Dodaje do talii karty podanego typu i w podanej ilości
     */
    public LinkedList<Akcja> dodaj(Akcja typ, int ilość) {
    }

    public void tasuj() {
        //tasuje zużyte i wstawia je do puli
    }
}

