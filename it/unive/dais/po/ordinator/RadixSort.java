package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;

public class RadixSort<T> {
    private ArrayList<T> input, output;
    private ArrayList<CountingSort<T>> steps;

    public RadixSort() {
        input = new ArrayList<T>();
        output = null;
        steps = new ArrayList<CountingSort<T>>();
    }

    public void addData(T data) {
        input.add(data);
    }

    public void addData(Collection<T> dataCollection) {
        input.addAll(dataCollection);
    }

    public void addStep(CountingSort<T> step) {
        steps.add(step);
    }

    public ArrayList<T> sort() {
        output = new ArrayList<T>(input);
        for (CountingSort<T> step : steps) {
            step.addData(output);
            output = step.sort();
        }
        return output;
    }


}
