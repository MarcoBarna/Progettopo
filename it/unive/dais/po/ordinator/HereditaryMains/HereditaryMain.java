package it.unive.dais.po.ordinator.HereditaryMains;

import it.unive.dais.po.ordinator.ComparingOrdinators.*;

import java.util.Comparator;
import java.util.Random;

public class HereditaryMain {

    public static void main(String[] args){
        HereditaryOrdinator<Integer> o = new HereditaryMergeSort<>();
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null)
                    return -1;
                else
                    if (o2 == null)
                        return 1;
                    else
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
