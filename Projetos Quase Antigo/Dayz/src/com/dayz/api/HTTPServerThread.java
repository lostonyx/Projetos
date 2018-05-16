package com.dayz.api;



import java.io.IOException;
import java.net.*;

import com.dayz.Main;

public class HTTPServerThread extends Thread {

    private Main plugin;

    private int port;
    private ServerSocket socket;

    public HTTPServerThread(Main plugin) throws IOException {
        this.plugin = plugin;
        socket = new ServerSocket(2165,10);
        System.out.println("Opened server on port 2165.");
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket connected = socket.accept();
                (new HTTPClientThread(plugin,connected)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopSocket(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}