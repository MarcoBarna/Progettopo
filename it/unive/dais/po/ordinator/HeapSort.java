package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class HeapSort<T> implements Ordinator<T> {

    private ArrayList<T> maxHeap;
    private Comparator<T> myComparator;
    private int heapSize;

    private boolean sorted;

    public HeapSort() {
        maxHeap = new ArrayList<>();
        myComparator = null;
        heapSize = 0;
    }

    @Override
    public void addData(Collection<T> dataCollection) {
        sorted = false;
        maxHeap.addAll(dataCollection);
        heapSize = maxHeap.size();
    }

    @Override
    public void addData(T data) {
        sorted = false;
        maxHeap.add(data);
        heapSize = maxHeap.size();
    }

    @Override
    public Integer getDataSize() {
        return maxHeap.size();
    }

    @Override
    public void addComparator(Comparator<T> comp) {
        myComparator = comp;
    }


    //funzioni per l'heapsort
    private void buildMaxHeap(ArrayList<T> heap) {
        for (int i = heapSize /2; i >= 0 ; i--) {
            maxHeapify(heap, i);
        }
    }

    private void maxHeapify(ArrayList<T> maxHeap, int i) {
        int l, r, max;
        T aux;

        l = i*2+1;
        r = (i+1)*2;
        max = i;

        if(l < heapSize && myComparator.compare(maxHeap.get(l), maxHeap.get(i)) == 1)
            max = l;
        if(r < heapSize && myComparator.compare(maxHeap.get(r), maxHeap.get(max)) == 1)
            max = r;

        if(i != max){
            aux = maxHeap.get(i);
            maxHeap.set(i, maxHeap.get(max));
            maxHeap.set(max, aux);
            maxHeapify(maxHeap, max);
        }
    }

    @Override
    public void sort() throws NoComparatorFound {

        if(myComparator == null) throw new NoComparatorFound("You forgot to add a comparator");

        T aux;

        buildMaxHeap(maxHeap);
        for (int i = maxHeap.size()-1; i > 0 ; i--) {
            aux = maxHeap.get(0);
            maxHeap.set(0, maxHeap.get(i));
            maxHeap.set(i, aux);
            heapSize = heapSize - 1;
            maxHeapify(maxHeap, 0);
        }
    }

    @Override
    public void sort(Comparator<T> comp) throws NoComparatorFound {

    }

    @Override
    public ArrayList<T> getArrayListCopy() {
        return new ArrayList<T>(maxHeap);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
