package it.unive.dais.po.ordinator.OrdinatorsInterface;

import it.unive.dais.po.ordinator.Exceptions.NoComparatorFound;

import java.util.ArrayList;

public class HeapTest {

    public static void main(String args[]) throws NoComparatorFound {

        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(16);
        myList.add(4);
        myList.add(10);
        myList.add(14);
        myList.add(7);
        myList.add(9);
        myList.add(3);
        myList.add(2);
        myList.add(8);
        myList.add(1);

        System.out.println(myList);

        HeapSort<Integer> myHeapSorter = new HeapSort<>();
        myHeapSorter.addData(myList);
        myHeapSorter.addComparator((Integer a, Integer b) -> (a.compareTo(b)));
        myHeapSorter.sort();

        System.out.println(myHeapSorter.getArrayListCopy());
    }
}
