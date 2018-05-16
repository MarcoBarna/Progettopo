package it.unive.dais.po.ordinator;

import java.util.ArrayList;

public class MainRadixSort {
    public static void main(String[] args) {
        ArrayList<Integer> array;

        System.out.println("HELLO");
        System.out.println("NUOVA LIBRERIA AVANZATA DI ORDINAMENTO");

        RadixSort<Integer> sorter = new RadixSort<Integer>();

        CountingSort<Integer>[] steps = new CountingSort[4];
        for (Integer i = 0; i < 4; i++)
            steps[i] = new CountingSort<Integer>();

        steps[0].addExtractor(new Extractor<Integer>() {
            @Override
            public Integer getValue(Integer elem) {
                return elem % 10;
            }
        });

        steps[1].addExtractor(new Extractor<Integer>() {
            @Override
            public Integer getValue(Integer elem) {
                return (elem/10) % 10;
            }
        });

        steps[2].addExtractor(new Extractor<Integer>() {
            @Override
            public Integer getValue(Integer elem) {
                return (elem/100) % 10;
            }
        });

        steps[3].addExtractor(new Extractor<Integer>() {
            @Override
            public Integer getValue(Integer elem) {
                return (elem/1000) % 10;
            }
        });


        sorter.addData(98);
        sorter.addData(10);
        sorter.addData(7);
        sorter.addData(9);
        sorter.addData(198);
        sorter.addData(6);
        sorter.addData(1000);
        sorter.addData(55);
        sorter.addData(1);
        sorter.addData(132);
        sorter.addData(98);
        sorter.addData(16);

//        for (CountingSort<Integer> step : steps) {
//            sorter.addStep(step);
//        }
        sorter.addStep(steps[0]);

        array = sorter.sort();

        for(Integer item : array)
            System.out.println(item);
    }
}
