package it.unive.dais.po.ordinator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public interface Ordinator2<T> extends Iterable<T>{

    public void addData(Collection<T> dataCollection);

    public void addData(T data);

    public Integer getDataSize();

    public void addComparator(Comparator<T> comp);

    public void sort() throws NoComparatorFound;

    public void sort(Comparator<T> comp) throws NoComparatorFound;

    public ArrayList<T> getArrayListCopy();
}
