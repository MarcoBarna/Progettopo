package it.unive.dais.po.ordinator.Mains;

import it.unive.dais.po.ordinator.ComparingOrdinators.HereditaryInsertionSort;

import java.util.Comparator;
import java.util.Random;

public class HereditaryMain {

    public static void main(String[] args){
        HereditaryInsertionSort<Integer> o = new HereditaryInsertionSort<>();
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };

        Random rand = new Random();
        for (int i = 0; i < 30; i++)
            o.add(rand.nextInt(100));

        o.setMyComparator((Integer i, Integer j) -> i - j);

        try {
            o.sort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(o);
    }
}
