package hu.petrik;

import java.util.Comparator;

public class NevsorSzerintiComparator implements Comparator<Versenyzo> {
    @Override
    public int compare(Versenyzo o1, Versenyzo o2) {
        return o1.getNev().compareToIgnoreCase(o2.getNev());
    }
}
