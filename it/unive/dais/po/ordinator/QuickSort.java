package it.unive.dais.po.ordinator;

import java.util.*;

public class QuickSort<T> implements Ordinator<T> {

    private ArrayList<T> myArrayList;
    private Comparator<T> myComparator;

    private Random random = new Random();

    private boolean sorted;

    private class Couple{
        public Integer q;
        public Integer t;
    }

    public QuickSort(){
        myArrayList = new ArrayList<T>();
        myComparator = null;
        sorted = false;
    }

    public boolean isSorted() {
        return sorted;
    }

    @Override
    public void addData(Collection<T> dataCollection) {
        sorted = false;
        myArrayList.addAll(dataCollection);
    }

    @Override
    public void addData(T data) {
        sorted = false;
        myArrayList.add(data);
    }

    @Override
    public Integer getDataSize() {
        return myArrayList.size();
    }

    @Override
    public void addComparator(Comparator<T> comp) {
        sorted = false;
        myComparator = comp;
    }

    private void scambia(Integer a, Integer b){
        T temp = myArrayList.get(a);
        myArrayList.set(a, myArrayList.get(b));
        myArrayList.set(b, temp);
    }

    private void partition(Couple coppia, Integer inizio, Integer fine){
        Integer min, eq, mag;
        T pivot = myArrayList.get(fine);
        min = eq = inizio;
        mag = fine;
        while(eq < mag)
            if(myComparator.compare(myArrayList.get(eq), pivot) < 0) {
                scambia(eq, min);
                min++;
                eq++;
            }
            else
                if(myComparator.compare(myArrayList.get(eq), pivot) == 0)
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
        randomizedQuicksort(0, myArrayList.size()-1 );
        sorted = true;
    }

    @Override
    public void sort(Comparator<T> comp) throws NoComparatorFound {
        addComparator(comp);
        sort();
    }

    @Override
    public ArrayList<T> getArrayListCopy() {
        return new ArrayList<T>(myArrayList);
    }

    @Override
    public Iterator<T> iterator() {
        return sorted ? myArrayList.iterator()
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
