package io;

abstract class OrderManagerFileSource implements FileSource {
    private String filePath;

    public void setPath(String path){
        this.filePath = path;
    }

    public String getPath() {
        return filePath;
    }
}
