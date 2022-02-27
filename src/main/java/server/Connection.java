package server;

import file.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Connection extends Thread {
    public static final List<Connection> connections = Collections.synchronizedList(new ArrayList<>());
    public final Logger file = Logger.getInstance();
    public BufferedReader in;
    public PrintWriter out;

    public Connection(Socket socket) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String name = in.readLine();
            file.log(name + " в сети");
            sendTextAll(name + " в сети");

            String msg;
            while (true) {
                msg = in.readLine();
                if ("exit".equals(msg)) {
                    break;
                }
                file.log(name + ": " + msg);
                sendTextAll(name + " : " + msg);
            }
            file.log(name + " покинул чат");
            sendTextAll(name + " покинул чат");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendTextAll(String msg) {
        synchronized (connections) {
            for (Connection connection : connections) {
                connection.out.println(msg);
            }
        }
    }
}
