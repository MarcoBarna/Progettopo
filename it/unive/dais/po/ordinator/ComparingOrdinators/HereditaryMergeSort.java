package it.unive.dais.po.ordinator.ComparingOrdinators;

import java.util.ArrayList;
import java.util.Comparator;

public class HereditaryMergeSort<T> extends HereditaryOrdinator<T> {

    private ArrayList<T> arrayMerge(Comparator<? super T> c, ArrayList<T> merged, ArrayList<T> half1, ArrayList<T> half2) {
        Integer i1, i2;

        for (i1 = 0, i2 = 0; i1 < half1.size() && i2 < half2.size();)
                merged.set(i1+i2, ((half1.get(i1) != null && half2.get(i2) != null && c.compare(half1.get(i1), half2.get(i2)) <= 0) || half2.get(i2) == null) ? half1.get(i1++) : half2.get(i2++));

        return merged;
    }

    private void mergeSort(Comparator<? super T> c, ArrayList<T> source){
        ArrayList<T> half1, half2;

        if(source.size() > 1) {
            mergeSort(c, half1 = new ArrayList<T>(source.subList(0, source.size() / 2)));
            mergeSort(c, half2 = new ArrayList<T>(source.subList(source.size() / 2, source.size())));

            arrayMerge(c, source, half1, half2);
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        mergeSort(c, this);
        setSorted(true);
    }
}
