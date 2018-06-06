package it.unive.dais.po.ordinator.OrdinatorsInterface;

public interface Extractor<T> {

    // estrae il valore dell'oggetto elem
    public Integer getValue(T elem);
}
