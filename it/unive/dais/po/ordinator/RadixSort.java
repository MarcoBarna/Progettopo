package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;

public class RadixSort<T> {
    private ArrayList<T> input, output;
    private ArrayList<CountingSort<T>> steps;
    private boolean sorted;

    public RadixSort() {
        input = new ArrayList<T>();
        output = null;
        steps = new ArrayList<CountingSort<T>>();
        sorted = false;
    }

    public void addData(T data) {
        input.add(data);
        sorted = false;
    }

    public void addData(Collection<T> dataCollection) {
        input.addAll(dataCollection);
        sorted = false;
    }

    public void addStep(CountingSort<T> step) {
        steps.add(step);
        sorted = false;
    }

    public ArrayList<T> sort() {
        if (sorted == false) {
            output = new ArrayList<T>(input);
            for (CountingSort<T> step : steps) {
                step.addData(output);
                output = step.sort();
            }
            sorted = true;
        }
        return new ArrayList<T>(output);
    }


}
