package it.unive.dais.po.ordinator.NotOrdinatorsInterface;

import java.util.ArrayList;
import java.util.Collection;

public class CountingSort<T> {

    // valori minimi e massimi, numero dei valori diversi possibili
    private Integer minValue, maxValue;
    private Integer range;

    private Extractor<T> extractor; //estrae il valore in formato intero di un oggetto di tipo parametrico
    private ArrayList<T> input, output; //ArrayList che salvano 'input e l'output
    private Integer[] countingArray; //array che memorizza le posizioni in cui inserire gli oggetti
                                     //che hanno valore equivalente all'indirizzo del countingArray

    private boolean sorted;

    //costruttore
    public CountingSort(){
        input = new ArrayList<T>();
        output = null;
        countingArray = null;
        extractor = null;
        sorted = false;
    }

    //aggiunge gli oggetti contenuti in una collection dentro l'array di input
    public void addData(Collection<T> dataCollection){
        input.addAll(dataCollection);
        sorted = false;
    }

    //aggiunge un singolo elemento dentro l'array di input
    public void addData(T data){
        input.add(data);
        sorted = false;
    }

    //restituisce la dimensione dell'input
    public Integer getDataSize(){
        return input.size();
    }

    //aggiunge un Extractor al CountingSort
    public void addExtractor (Extractor<T> extractor){
        this.extractor = extractor;
        sorted = false;
    }

    //cerca nell'input alla ricerca del minimo e del massimo, successivamente calcola il range
    private void exploreInput(){
        minValue = maxValue = extractor.getValue(input.get(0));
        for (T item : input) {
            if (extractor.getValue(item) < minValue)
                minValue = extractor.getValue(item);
            if (extractor.getValue(item) > maxValue)
                maxValue = extractor.getValue(item);
        }
        range = maxValue - minValue + 1;
    }

    //analizza l'input e lo mette in output, poi restituisce l'output
    public ArrayList<T> sort(){
        if (sorted == false) {
            output = new ArrayList<T>(input);
            exploreInput();

            countingArray = new Integer[range];

            for (Integer i = 0; i < countingArray.length; i++)
                countingArray[i] = 0;

            for (T item : input)
                countingArray[extractor.getValue(item) - minValue]++;

            for (Integer i = 1; i < countingArray.length; i++)
                countingArray[i] += countingArray[i - 1];

            for (Integer i = input.size() - 1; i >= 0; i--) {
                output.set(--countingArray[extractor.getValue(input.get(i)) - minValue], input.get(i));
            }

            sorted = true;
        }

        return new ArrayList<T>(output);
    }
}