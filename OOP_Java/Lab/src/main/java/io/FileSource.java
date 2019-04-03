package io;

import barBossHouse.Order;

public interface FileSource extends Source<Order> {
    void setPath(String path);

    String getPath();
}
