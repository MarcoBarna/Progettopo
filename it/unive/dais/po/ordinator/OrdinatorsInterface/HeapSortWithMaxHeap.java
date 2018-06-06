package it.unive.dais.po.ordinator.OrdinatorsInterface;

import it.unive.dais.po.ordinator.Exceptions.NoComparatorFound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class HeapSortWithMaxHeap<T> implements Ordinator<T> {

    private class MaxHeap<S> extends ArrayList<S>{

        private Comparator<S> myComparator;
        private int heapSize;

        public MaxHeap(){
            super();
            myComparator = null;
            heapSize = 0;
        }

        public int leftSon(int index){
            return (index+1)*2 - 1;
        }

        public int rightSon(int index){
            return (index+1)*2;
        }

        /*public int parent(int index){
            return (index+1)/2 - 1;
        }*/

        private void build() throws NoComparatorFound {
            heapSize = size();
            if (myComparator == null) throw new NoComparatorFound("MaxHeap.build, manca comparator.");
            for (int i = heapSize /2; i >= 0 ; i--) {
                maxHeapify( i);
            }
        }

        private void maxHeapify(int index) {
            int l, r, max;
            S aux;

            l = leftSon(index);
            r = rightSon(index);
            max = index;

            if(l < heapSize && myComparator.compare(get(l), get(index)) > 0)
                max = l;
            if(r < heapSize && myComparator.compare(get(r), get(max)) > 0)
                max = r;

            if(index != max){
                aux = get(index);
                set(index, get(max));
                set(max, aux);
                maxHeapify(max);
            }
        }

        public S extractMax(){
            S max = get(0);
            set(0, get(size()-1));
            maxHeapify(0);
            return max;
        }

        public int getHeapSize(){
            return heapSize;
        }

        public void setMyComparator(Comparator<S> myComparator) {
            this.myComparator = myComparator;
        }
    }

    private MaxHeap<T> maxHeap;
    private ArrayList<T> myArrayList;
    private Comparator<T> myComparator;

    private boolean sorted;

    public HeapSortWithMaxHeap() {
        maxHeap = new MaxHeap<T>();
        myComparator = null;
        sorted = false;
    }

    @Override
    public void addData(Collection<T> dataCollection) {
        sorted = false;
        maxHeap.addAll(dataCollection);
    }

    @Override
    public void addData(T data) {
        sorted = false;
        maxHeap.add(data);
    }

    @Override
    public Integer getDataSize() {
        return maxHeap.size();
    }

    @Override
    public void addComparator(Comparator<T> comp) {
        myComparator = comp;
    }

    @Override
    public void sort() throws NoComparatorFound {
        maxHeap.setMyComparator(myComparator);
        maxHeap.build();
        for ( int i = maxHeap.size()-1; i >= 0; i--)
            maxHeap.add(maxHeap.getHeapSize(),maxHeap.extractMax());
        sorted = true;
    }

    @Override
    public void sort(Comparator<T> comp) throws NoComparatorFound {
        myComparator = comp;
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
