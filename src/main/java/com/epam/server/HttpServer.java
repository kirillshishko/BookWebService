package com.epam.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer implements AbstractServer{

    private ExecutorService service;
    private int port;
    private int sizeOfPool;

    public HttpServer(){}

    public HttpServer(int port, int sizeOfPool ){
        this.sizeOfPool = sizeOfPool;
        this.port = port;

    }

    public void start() throws IOException {
        service = Executors.newFixedThreadPool(port);
        ServerSocket socket = new ServerSocket(port);

         while (true){
            Socket sc = socket.accept();
            service.submit(new SocketHandler(sc));

        }
    }
    public void close() {
        service.shutdown();
    }

}
