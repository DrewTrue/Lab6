package io;

import humanResources.*;

public interface FileSource extends Source<EmployeeGroup> {
    public void setPath(String path);
    public String getPath();
}