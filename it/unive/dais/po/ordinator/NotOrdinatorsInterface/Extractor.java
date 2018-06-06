package it.unive.dais.po.ordinator.NotOrdinatorsInterface;

public interface Extractor<T> {

    // estrae il valore dell'oggetto elem
    public Integer getValue(T elem);
}
