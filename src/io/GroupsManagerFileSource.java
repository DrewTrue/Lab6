package io;

public abstract class GroupsManagerFileSource implements FileSource{
    private String path;

    public GroupsManagerFileSource(String path){
        this.path = path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
}
