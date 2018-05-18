package io;

public interface Source<T> {
    public void load(T t);
    public void store(T t);
    boolean delete(T t);
    boolean create(T t);
}
