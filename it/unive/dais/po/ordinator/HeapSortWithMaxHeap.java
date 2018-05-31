package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class HeapSortWithMaxHeap<T> implements Ordinator<T> {

    private class MaxHeap<S>{
        private ArrayList<S> heap;
        private Comparator<S> comparator;

        public MaxHeap() {
            heap = new ArrayList<S>();
            comparator = null;
        }

        public void addData(Collection<S> dataCollection) {
            heap.addAll(dataCollection);
        }

        public void addData(S data) {
            heap.add(data);
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

        private void build() throws NoComparatorFound{
            comparator = (Comparator<S>) myComparator;
            if (myComparator == null) throw new NoComparatorFound("MaxHeap.build, manca comparator.");
            for (int i = heap.size() /2; i >= 0 ; i--) {
                maxHeapify( i);
            }
        }

        private void maxHeapify(int index) {
            int l, r, max;
            S aux;

            l = leftSon(index);
            r = rightSon(index);
            max = index;

            if(l < heap.size() && comparator.compare(heap.get(l), heap.get(index)) > 0)
                max = l;
            if(r < heap.size() && comparator.compare(heap.get(r), heap.get(max)) > 0)
                max = r;

            if(index != max){
                aux = heap.get(index);
                heap.set(index, heap.get(max));
                heap.set(max, aux);
                maxHeapify(max);
            }
        }

        public S extractMax(){
            S max = heap.get(0);
            heap.set(0, heap.get(heap.size()-1));
            heap.remove(heap.size()-1);
            maxHeapify(0);
            return max;
        }

        public Integer size() {
            return heap.size();
        }
    }

    private MaxHeap<T> maxHeap;
    private ArrayList<T> myArrayList;
    private Comparator<T> myComparator;

    private boolean sorted;

    public HeapSortWithMaxHeap() {
        maxHeap = new MaxHeap<T>();
        myArrayList = new ArrayList<T>();
        myComparator = null;
        sorted = false;
    }

    @Override
    public void addData(Collection<T> dataCollection) {
        sorted = false;
        maxHeap.addData(dataCollection);
    }

    @Override
    public void addData(T data) {
        sorted = false;
        maxHeap.addData(data);
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
        maxHeap.build();
        myArrayList = new ArrayList<>(maxHeap.size());
        for ( int i = maxHeap.size()-1; i >= 0; i--) {
            //System.out.println(maxHeap.extractMax());
            myArrayList.add(maxHeap.extractMax());
        }
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
