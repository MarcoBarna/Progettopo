package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class InsertionSort<T> implements Ordinator<T> {

    private ArrayList<T> myArrayList;
    private Comparator<T> myComparator;

    private boolean sorted;

    public InsertionSort() {
        myArrayList = new ArrayList<T>();
        myComparator = null;
        sorted = false;
    }

    public boolean isSorted() {
        return sorted;
    }

    @Override
    public void addData(Collection<T> dataCollection) throws RuntimeException {
        sorted = false;
            if(myArrayList.addAll(dataCollection) == false)
                throw new RuntimeException("addData(Collection.addAll) failed");
    }

    @Override
    public void addData(T data) throws RuntimeException {
        sorted = false;
        if(myArrayList.add(data) == false)
            throw new RuntimeException("addData(Collection.addAll) failed");
    }

    @Override
    public Integer getDataSize() {
        return myArrayList.size();
    }

    @Override
    public void addComparator(Comparator<T> comparator) {
        myComparator = comparator;
    }

    @Override
    public void sort() throws NoComparatorFound {
        Integer i, j;
        if(myComparator == null)
            throw new NoComparatorFound("You forgot to add a comparator");

        for(j = 1; j < myArrayList.size(); j++) {
            T key = myArrayList.get(j);

            i = j - 1;

            while(i >= 0 && myComparator.compare(myArrayList.get(i), key) > 0) {
                myArrayList.set(i+1, myArrayList.get(i));
                i--;
            }

            myArrayList.set(i + 1, key);
        }
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
        return (sorted) ? myArrayList.iterator()
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
