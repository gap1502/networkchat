package server;

import file.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final Logger file = Logger.getInstance();
    public Socket socket;
    public ServerSocket serverSocket;

    public void toListen(int port) {
        try {
            serverSocket = new ServerSocket(port);
            file.log("Сервер запущен!!!");
            System.out.println("Сервер запущен!!!");

            while (true) {
                socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                Connection.connections.add(connection);
                connection.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
