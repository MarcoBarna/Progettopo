package it.unive.dais.po.ordinator;

import java.util.*;

public class QuickSort<T> implements Ordinator2<T> {
    ArrayList<T> myArray;
    Comparator<T> myComparator;

    Random random = new Random();

    private boolean sorted;

    private class Couple{
        public Integer q;
        public Integer t;
    }

    public QuickSort(){
        myArray = new ArrayList<T>();
        myComparator = null;
        sorted = false;
    }


    @Override
    public void addData(Collection<T> dataCollection) {
        sorted = false;
        myArray.addAll(dataCollection);
    }

    @Override
    public void addData(T data) {
        sorted = false;
        myArray.add(data);
    }

    @Override
    public Integer getSize() {
        return myArray.size();
    }

    @Override
    public void addComparator(Comparator<T> comp) {
        sorted = false;
        myComparator = comp;
    }

    private void scambia(Integer a, Integer b){
        T temp = myArray.get(a);
        myArray.set(a, myArray.get(b));
        myArray.set(b, temp);
    }

    private void partition(Couple coppia, Integer inizio, Integer fine){
        Integer min, eq, mag;
        T pivot = myArray.get(fine);
        min = eq = inizio;
        mag = fine;
        while(eq < mag)
            if(myComparator.compare(myArray.get(eq), pivot) < 0) {
                scambia(eq, min);
                min++;
                eq++;
            }
            else
                if(myComparator.compare(myArray.get(eq), pivot) == 0)
                    eq++;
                else{
                    mag--;
                    scambia(eq, mag);
                }
        scambia(mag, fine);

        coppia.q = min;
        coppia.t = mag;
    }

    private void randomizedQuicksort(Integer inizio, Integer fine){
        Couple coppia = new Couple();
        if (inizio < fine){
            //randomizedPartition
                Integer r = inizio + random.nextInt(fine-inizio);
                scambia(r, fine);

            partition(coppia, inizio, fine);
            randomizedQuicksort(inizio, coppia.q - 1);
            randomizedQuicksort(coppia.t + 1, fine);
        }
    }

    @Override
    public void sort() throws NoComparatorFound {
        if(myComparator == null)
            throw new NoComparatorFound("You forgot to add a comparator");
        randomizedQuicksort(0, myArray.size()-1 );
        sorted = true;
    }

    @Override
    public void sort(Comparator<T> comp) throws NoComparatorFound {
        addComparator(comp);
        sort();
    }

    @Override
    public ArrayList<T> getArrayListCopy() {
        return new ArrayList<T>(myArray);
    }

    @Override
    public Iterator<T> iterator() {
        return sorted ? myArray.iterator()
                      : new Iterator<T>() {
                            @Override
                            public boolean hasNext() {
                                return false;
                            }

                            @Override
                            public T next() {
                                return null;
                            }
                        };
    }
}
