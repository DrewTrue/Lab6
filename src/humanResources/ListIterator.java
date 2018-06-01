package humanResources;

import java.util.*;

//todo это ПРИВАТНЫЙ ВНУТРЕННИЙ класс
public class ListIterator<T> implements java.util.ListIterator<T> {
    Node current;

    private final static int DEFAULT_INDEX = 0;

    public ListIterator() {
        this.list = list;
        this.size = list.length;
        this.index = DEFAULT_INDEX;
    }

    public ListIterator(int index) {
        this.list = list;
        this.size = list.length;
        this.index = index;
    }



    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public T previous() {
        return null;
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public int previousIndex() {
        return 0;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(T t) {

    }

    @Override
    public void add(T t) {

    }
}