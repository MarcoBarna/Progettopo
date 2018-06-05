package it.unive.dais.po.ordinator.ComparingOrdinators;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class HereditaryOrdinator<O> extends ArrayList<O> {
    private Comparator<? super O> myComparator;
    private boolean sorted;

    public HereditaryOrdinator(){
        super();
        myComparator = null;
        sorted = true;
    }

    public boolean isSorted(){
        return sorted;
    }

    protected void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public Comparator<? super O> getMyComparator(){
        return myComparator;
    }

    public void setMyComparator(Comparator<O> myComparator) {
        this.myComparator = myComparator;
    }

//    private boolean checkIfSorted(Comparator<? super O> comparator){
//        boolean srtd = true;
//        for (int i = 0; i < size() - 2; i++)
//            srtd = srtd && comparator.compare(get(i), get(i + 1)) <= 0;
//        return srtd;
//    }

    @Override
    public void add(int index, O e) {
        sorted = false;
        super.add(index, e);
    }

    @Override
    public boolean add(O e) {
        sorted = false;
        return super.add(e);
    }

    @Override
    public boolean addAll(int index, Collection<? extends O> c) {
        sorted = false;
        return super.addAll(index, c);
    }

    @Override
    public boolean addAll(Collection<? extends O> c) {
        sorted = false;
        return super.addAll(c);
    }

    @Override
    public void sort(Comparator<? super O> c){
        super.sort(c);
        sorted = true;
    }

    public void sort() throws Exception{
        if (myComparator == null)
            throw new Exception("it.unive.dais.po.ordinator.ComparingOrdinators.HereditaryOrdinator.sort() -> No Comparator Found!");
        sort(myComparator);
    }
}
