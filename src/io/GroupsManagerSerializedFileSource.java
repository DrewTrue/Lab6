package io;

public class GroupsManagerSerializedFileSource extends GroupsManagerFileSource {
    public GroupsManagerSerializedFileSource(String path) {
        super(path);
    }

    @Override
    public void load(Object o) {

    }

    @Override
    public void store(Object o) {

    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public boolean create(Object o) {
        return false;
    }
}
