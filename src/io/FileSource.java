package io;

public interface FileSource<T> extends Source<T> {
    public void setPath(String path);
    public String getPath();
}
