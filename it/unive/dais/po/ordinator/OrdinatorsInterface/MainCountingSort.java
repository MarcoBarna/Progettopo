package it.unive.dais.po.ordinator.OrdinatorsInterface;

import java.util.ArrayList;

public class MainCountingSort {
    public static void main(String[] args) {
        ArrayList<String> array;

        System.out.println("HELLO");
        System.out.println("NUOVA LIBRERIA AVANZATA DI ORDINAMENTO");

        CountingSort<String> sorter = new CountingSort<String>();
        sorter.addExtractor(new Extractor<String>() {

            @Override
            public Integer getValue(String elem) {
                return elem.length();
            }
        });

        sorter.addData("cane");
        sorter.addData("casa");
        sorter.addData("pincopallino");
        sorter.addData("2");
//        sorter.addData(3);
//        sorter.addData(6);
//        sorter.addData(-2);
//        sorter.addData(55);
//        sorter.addData(1);
//        sorter.addData(-14);
//        sorter.addData(9);
//        sorter.addData(3);

        array = sorter.sort();

        for(String item : array)
            System.out.println(item);
    }
}
