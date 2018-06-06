package it.unive.dais.po.ordinator.HereditaryOrdinators;

import java.util.Comparator;

public class HereditaryHeapSort<T> extends HereditaryOrdinator<T> {

    //variabile che usiamo per l'heapsort
    private int heapSize;

    //funzioni per l'heapsort
    private void buildMaxHeap(Comparator<? super T> c) {
        heapSize = size();
        for (int i = heapSize /2; i >= 0 ; i--) {
            maxHeapify(c, i);
        }
    }

    public int leftSon(int index){
        return (index+1)*2 - 1;
    }

    public int rightSon(int index){
        return (index+1)*2;
    }

    private void maxHeapify(Comparator<? super T> c, int index) {
        int l = leftSon(index);
        int r = rightSon(index);
        int max = index;

        if(l < heapSize && c.compare(get(l), get(index)) > 0)
            max = l;
        if(r < heapSize && c.compare(get(r), get(max)) > 0)
            max = r;

        if(index != max){
            swap(index, max);
            maxHeapify(c, max);
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        buildMaxHeap(c);

        for (int i = size()-1; i > 0 ; i--) {
            swap(0, i);
            heapSize--;
            maxHeapify(c, 0);
        }

        setSorted(true);
    }
}
