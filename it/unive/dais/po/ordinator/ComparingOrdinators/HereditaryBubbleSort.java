package it.unive.dais.po.ordinator.ComparingOrdinators;

import it.unive.dais.po.ordinator.NoComparatorFound;
import it.unive.dais.po.ordinator.Ordinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class HereditaryBubbleSort<T> extends HereditaryOrdinator<T> {

    @Override
    public void sort(Comparator<? super T> c) {
        while (!isSorted()){
            setSorted(true);
            for (int i = 1; i < size(); i++){
                    if(c.compare(get(i - 1), get(i)) > 0){
                        setSorted(false);
                        swap(i,i-1);
                    }
            }
        }
    }
}
