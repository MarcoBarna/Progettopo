package it.unive.dais.po.ordinator.HereditaryOrdinators;

import it.unive.dais.po.ordinator.Exceptions.NoComparatorFound;
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

    protected void swap(int i, int j){

        set(i, set(j, get(i)));

//        O a = get(i); // salva temporaneamente l'elemento in pos i
//        set(i, get(j)); // scambia i con j
//        set(j,a); // scambia j con a
    }

    @Override
    public void add(int index, O e) {
        setSorted(false);
        super.add(index, e);
    }

    @Override
    public boolean add(O e) {
        setSorted(false);
        return super.add(e);
    }

    @Override
    public boolean addAll(int index, Collection<? extends O> c) {
        setSorted(false);
        return super.addAll(index, c);
    }

    @Override
    public boolean addAll(Collection<? extends O> c) {
        setSorted(false);
        return super.addAll(c);
    }

    @Override
    public O set(int index, O element){
        setSorted(false);
        return super.set(index, element);
    }

    @Override
    public void sort(Comparator<? super O> c){
        super.sort(c);
        setSorted(true);
    }

    public void sort() throws Exception{
        if (myComparator == null)
            throw new NoComparatorFound("it.unive.dais.po.ordinator.HereditaryOrdinators.HereditaryOrdinator.sort() -> No Comparator(" + getMyComparator() + ") Found!");
        sort(myComparator);
    }
}
