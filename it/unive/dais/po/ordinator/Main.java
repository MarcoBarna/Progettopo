package it.unive.dais.po.ordinator;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        System.out.println("HELLO");
        System.out.println("NUOVA LIBRERIA AVANZATA DI ORDINAMENTO");

        Ordinator<Integer> myOrdinator = new InsertionSort<Integer>();
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
            Integer[] a = myOrdinator.sort();

            for(Object item : a)
                System.out.println(item);
        } catch (NoComparatorFound noComparatorFound) {
            noComparatorFound.printStackTrace();
        }


    }
}
