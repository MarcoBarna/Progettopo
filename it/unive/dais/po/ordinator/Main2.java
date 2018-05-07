package it.unive.dais.po.ordinator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Main2 {
    public static void main(String[] args) {
        ArrayList<Integer> array;

        System.out.println("HELLO");
        System.out.println("NUOVA LIBRERIA AVANZATA DI ORDINAMENTO");

        //Ordinator2<Integer> myOrdinator = new InsertionSort2<Integer>();
        //Ordinator2<Integer> myOrdinator = new QuickSort2<Integer>();
        Ordinator2<Integer> myOrdinator = new MergeSort2<Integer>();
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
