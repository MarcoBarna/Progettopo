package it.unive.dais.po.ordinator;

import java.util.Collection;
import java.util.Comparator;

public interface Ordinator<T> {

    public void addData(Collection<T> data);

    public void addData(T data);

    public void addComparator(Comparator<T> comp);

    public T[] sort() throws NoComparatorFound;

    public T[] sort(Comparator<T> comp);

    public void sort(Collection<T> destination) throws NoComparatorFound, NeedEmptyCollection;

    public void sort(Comparator<T> comp, Collection<T> destination) throws NeedEmptyCollection;

}
