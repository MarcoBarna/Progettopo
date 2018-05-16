package it.unive.dais.po.ordinator;

public interface Extractor<T> {

    // estrae il valore dell'oggetto elem
    public Integer getValue(T elem);
}
