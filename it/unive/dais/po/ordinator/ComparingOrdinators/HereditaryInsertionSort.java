package it.unive.dais.po.ordinator.ComparingOrdinators;

import java.util.Comparator;

public class HereditaryInsertionSort<T> extends HereditaryOrdinator<T> {

    @Override
    public void sort(Comparator<? super T> comp) {
        Integer i, j;

        for(j = 1; j < size(); j++) {
            T key = get(j);

            i = j - 1;

            while(i >= 0 && comp.compare(get(i), key) > 0) {
                set(i+1, get(i));
                i--;
            }

            set(i + 1, key);
        }
        setSorted(true);
    }
}
