package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class BubbleSort<T> implements Ordinator<T> {
    private ArrayList<T> myArrayList;
    private Comparator<T> myComparator;
    private boolean sorted;

    public BubbleSort(){
        myArrayList = new ArrayList<>();
        myComparator = null;
        sorted = false;
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
        myComparator = comp;
    }

    private void swap(int i, int j){
        T a = myArrayList.get(i); // salva temporaneamente l'elemento in pos i
        myArrayList.set(i,myArrayList.get(j)); // scambia i con j
        myArrayList.set(j,a); // scambia j con a
    }

    @Override
    public void sort() throws NoComparatorFound {
        if(myComparator == null)
            throw new NoComparatorFound("You forgot to add a comparator");
        while (!sorted){
            sorted = true;
            for (int i = 1; i < myArrayList.size(); i++){
                    if(myComparator.compare(myArrayList.get(i - 1),myArrayList.get(i)) > 0){
                        sorted = false;
                        swap(i,i-1);
                    }
            }
        }
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
        return myArrayList.iterator();
    }
}
