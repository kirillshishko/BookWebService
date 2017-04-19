package com.epam.executor;

import com.epam.server.HttpServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer(8010, 10 );
        server.start();
    }
}
