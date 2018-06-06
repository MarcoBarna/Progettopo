package it.unive.dais.po.ordinator.OrdinatorsInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class MergeSort<T> implements Ordinator<T> {

    private ArrayList<T> myMessedArrayList, myOrderedArrayList;
    private Comparator<T> comparator;

    private boolean sorted;

    public MergeSort(){
        myMessedArrayList = new ArrayList<T>();
        myOrderedArrayList = null;
        comparator = null;
        sorted = false;
    }

    public boolean isSorted() {
        return sorted;
    }

    @Override
    public void addData(Collection<T> dataCollection) {
        myMessedArrayList.addAll(dataCollection);
        sorted = false;
    }

    @Override
    public void addData(T data) {
        myMessedArrayList.add(data);
        sorted = false;
    }

    @Override
    public Integer getDataSize() {
        return myMessedArrayList.size();
    }

    @Override
    public void addComparator(Comparator<T> comp) {
        comparator = comp;
    }

    private ArrayList<T> iteratorMerge1(ArrayList<T> half1, ArrayList<T> half2) {
        ArrayList<T> merged = new ArrayList<T>();
        Iterator<T> iter1 = half1.iterator(), iter2 = half2.iterator();
        T item1 = null, item2 = null;

        while (iter1.hasNext() || iter2.hasNext() || item1 != null || item2 != null){
            //gli iteratori hanno ancora oggetti o ho ancora elementi estratti da inserire.
            if (iter1.hasNext() && item1 == null)
                item1 = iter1.next();
            if (iter2.hasNext() && item2 == null)
                item2 = iter2.next();


            if ( item2 == null || (item1 != null && comparator.compare(item1, item2) <= 0) ){
                //item2 non esiste?
                //item2 esiste. item1 esiste? item1 <= item2?
                //inserisco item1
                merged.add(item1);
                item1 = null;
            } else {
                //item1 non esiste?
                // item1 > item2?
                // inserisco item2
                merged.add(item2);
                item2 = null;
            }

        }
        return merged;
    }

    private ArrayList<T> iteratorMerge2(ArrayList<T> half1, ArrayList<T> half2) {
        ArrayList<T> merged = new ArrayList<T>();
        Iterator<T> iter1 = half1.iterator(), iter2 = half2.iterator();
        T item1, item2;

        if (iter1.hasNext())
            item1 = iter1.next();
        else
            item1 = null;

        if (iter2.hasNext())
            item2 = iter2.next();
        else
            item2 = null;

        while (item1 != null || item2 != null){
            while  ( (item1 != null && item2 == null) || (item1 != null && comparator.compare(item1, item2) <= 0 )) {
                merged.add(item1);
                if (iter1.hasNext())
                    item1 = iter1.next();
                else
                    item1 = null;
            }

            while  ( (item1 == null && item2 != null) || (item2 != null && comparator.compare(item1, item2) > 0 )) {
                merged.add(item2);
                if (iter2.hasNext())
                    item2 = iter2.next();
                else
                    item2 = null;
            }
        }

        return merged;
    }

    private ArrayList<T> arrayMerge(ArrayList<T> half1, ArrayList<T> half2) {
        ArrayList<T> merged = new ArrayList<T>();

        Integer i1, i2;

        for (i1 = 0, i2 = 0; i1 < half1.size() && i2 < half2.size(); ){
            merged.add( comparator.compare(half1.get(i1), half2.get(i2)) <= 0 ? half1.get(i1++) : half2.get(i2++));
        }
        for (; i1 < half1.size(); i1++)
            merged.add(half1.get(i1));

        for (; i2 < half2.size(); i2++)
            merged.add(half2.get(i2));

        return merged;
    }

    private ArrayList<T> mergeSort(ArrayList<T> source){
        ArrayList<T> half1, half2;

        if(source.size() < 2)
            return source;
        else{
            half1 = mergeSort(new ArrayList<T>(source.subList(0, source.size() / 2)));
            half2 = mergeSort(new ArrayList<T>(source.subList(source.size() / 2, source.size())));

            return arrayMerge(half1, half2);
        }
    }

    @Override
    public void sort() throws NoComparatorFound {
        myOrderedArrayList = mergeSort(myMessedArrayList);
        sorted = true;
    }

    @Override
    public void sort(Comparator<T> comp) throws NoComparatorFound {
        addComparator(comp);
        sort();
    }

    @Override
    public ArrayList<T> getArrayListCopy() {
        return new ArrayList<T>(myOrderedArrayList);
    }

    @Override
    public Iterator<T> iterator() {
        return sorted ? myOrderedArrayList.iterator()
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
