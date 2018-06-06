package it.unive.dais.po.ordinator.HereditaryOrdinators;

import java.util.Comparator;

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
