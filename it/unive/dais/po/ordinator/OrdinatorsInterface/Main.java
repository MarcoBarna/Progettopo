package it.unive.dais.po.ordinator.OrdinatorsInterface;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> array;

        System.out.println("HELLO");
        System.out.println("NUOVA LIBRERIA AVANZATA DI ORDINAMENTO");

        //Ordinator<Integer> myOrdinator = new InsertionSort<Integer>();
        //Ordinator<Integer> myOrdinator = new QuickSort<Integer>();
        Ordinator<Integer> myOrdinator = new MergeSort<Integer>();
        myOrdinator.addComparator(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        myOrdinator.addData(5);
        myOrdinator.addData(4);
        myOrdinator.addData(6);
        myOrdinator.addData(9);
        myOrdinator.addData(0);
        myOrdinator.addData(3);
        myOrdinator.addData(6);
        myOrdinator.addData(7);
        myOrdinator.addData(1);
        myOrdinator.addData(7);
        myOrdinator.addData(9);
        myOrdinator.addData(3);

        try {
            myOrdinator.sort();
            array = myOrdinator.getArrayListCopy();
            for(Integer item : array)
                System.out.println(item);
        } catch (NoComparatorFound noComparatorFound) {
            noComparatorFound.printStackTrace();
        }


    }
}
