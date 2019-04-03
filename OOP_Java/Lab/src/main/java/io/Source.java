package io;

import java.io.IOException;

public interface Source<T> {
    void load(T t) throws IOException;

    void store(T t) throws IOException;

    void delete(T t) throws IOException;

    void create(T t) throws IOException;
}
