package server;

import configuration.Configuration;

public class MainServer {
    public static void main(String[] args) {
        Configuration configuration = Configuration.getInstance();
        Server server = new Server();
        server.toListen(configuration.getPort());
    }
}
