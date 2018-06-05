package it.unive.dais.po.ordinator.ComparingOrdinators;

import it.unive.dais.po.ordinator.NoComparatorFound;
import it.unive.dais.po.ordinator.Ordinator;

import java.util.*;

public class HereditaryQuickSort<T> extends HereditaryOrdinator<T> {

    private Random random;

    private class Couple{
        public Integer q;
        public Integer t;
    }

    public HereditaryQuickSort(){
        super();
        random = new Random();
    }

    private void partition(Comparator<? super T> c, Couple coppia, Integer inizio, Integer fine){
        Integer min, eq, mag;
        T pivot = get(fine);
        min = eq = inizio;
        mag = fine;
        while(eq < mag)
            if(c.compare(get(eq), pivot) < 0) {
                swap(eq, min);
                min++;
                eq++;
            }
            else
                if(c.compare(get(eq), pivot) == 0)
                    eq++;
                else{
                    mag--;
                    swap(eq, mag);
                }
        swap(mag, fine);

        coppia.q = min;
        coppia.t = mag;
    }

    private void randomizedQuicksort(Comparator<? super T> c, Integer inizio, Integer fine){
        Couple coppia = new Couple();
        if (inizio < fine){
            //randomizedPartition
                Integer r = inizio + random.nextInt(fine-inizio);
                swap(r, fine);

            partition(c, coppia, inizio, fine);
            randomizedQuicksort(c, inizio, coppia.q - 1);
            randomizedQuicksort(c, coppia.t + 1, fine);
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        randomizedQuicksort(c,0, size()-1 );
        setSorted(true);
    }
}
