package com.epam.server;

import java.io.IOException;

public interface AbstractServer {

    public void start() throws IOException;
    public void close();
}
