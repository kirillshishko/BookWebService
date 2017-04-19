package com.epam.server;

import com.epam.handle.DefHandler;
import com.epam.handle.Handler;
import com.epam.handle.IHandle;
import com.epam.methods.Request;
import com.epam.methods.Response;
import com.epam.utils.MatcherUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketHandler implements Runnable {

    private final List<Handler> handlers = new ArrayList<Handler>();
        private Request rq;
        private Response rp;
        private Socket socket;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

    private Handler findHendler(Request rq) throws IOException {
        Handler defHandler = new Handler(null, null, new DefHandler());
        String metnodFromRequest = rq.getMethod();
        String pathFromRequest = rq.getPath();

        for (Handler handler : handlers) {
            if (metnodFromRequest.equals(handler.getMethod()) && MatcherUtils.isMatches(handler.getUri(), pathFromRequest)) {
                return handler;
            }
        }
        return defHandler;
    }
    public void addHendler(String method, String path, IHandle handler) {
        handlers.add(new Handler(method, path, handler));
    }

        public void run() {
            try {
                rq = new Request(new BufferedReader(new InputStreamReader(socket.getInputStream())));
                rp = new Response(socket.getOutputStream());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {

                Handler handlerForInvoke = findHendler(rq);
                handlerForInvoke.getiHandle().handle(rq, rp);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


