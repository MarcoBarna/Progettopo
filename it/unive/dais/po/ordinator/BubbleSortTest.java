package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Comparator;

public class BubbleSortTest {
    public static void main(String[] args) {
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
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        bubbleSort.addData(myList);
        bubbleSort.sort();
        System.out.println(bubbleSort.getArrayListCopy());
    }
}
