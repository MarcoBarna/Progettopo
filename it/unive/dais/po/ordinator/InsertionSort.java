package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class InsertionSort<T> implements Ordinator<T> {

    Collection<T> myCollection;
    T[] myArray;
    Comparator<T> myComparator;

    public InsertionSort() {
        myCollection = new ArrayList<T>();
    }

    public InsertionSort(Comparator<T> comparator) {
        this();
        myComparator = comparator;
    }

    public InsertionSort(Collection<T> collection) {
        myCollection = collection;
    }

    public InsertionSort(Comparator<T> comparator, Collection<T> collection) {
        this(collection);
        addComparator(comparator);
    }



    @Override
    public void addData(Collection<T> data) throws RuntimeException {
        if(myCollection.addAll(data) == false)
            throw new RuntimeException("addData(Collection.addAll) failed");
    }

    @Override
    public void addData(T data) throws RuntimeException {
        if(myCollection.add(data) == false)
            throw new RuntimeException("addData(Collection.addAll) failed");
    }

    @Override
    public void addComparator(Comparator<T> comp) {
        myComparator = comp;
    }

    @Override
    public T[] sort() throws NoComparatorFound {
        myArray = (T[]) new Object[myCollection.size()];
        Integer i = 0, j;
        if(myComparator == null)
            throw new NoComparatorFound("You forgot to add a comparator");
        for(T item : myCollection){
            myArray[i] = item;
            i++;
        }

        for(j = 1; j < myArray.length; j++) {
            T key = myArray[j];

            i = j - 1;

            while(i >= 0 && myComparator.compare(myArray[i], key) > 0) {
                myArray[i+1] = myArray[i];
                i--;
            }

            myArray[i + 1] = key;
        }
        return myArray;
    }

    @Override
    public T[] sort(Comparator<T> comp) {
        T [] res;
        addComparator(comp);
        try {
            res = sort();
        } catch (NoComparatorFound noComparatorFound) {
            noComparatorFound.printStackTrace();
            res = null;
        }
        return res;
    }
}
