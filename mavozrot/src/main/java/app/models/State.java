package app.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class State {
    private String name;// we will numerate the candidate, in all the servers in al
    List<Integer> servers;
    int electors;

    public State(String name, int electors) {
        this.name = name;
        this.electors = electors;
        this.servers = new ArrayList<Integer>();
    }

    public void addServer(int serverPort) {
        servers.add(serverPort);
    }

    public String getName() {
        return name;
    }

    public int pickServer() {
        int size = servers.size();
        Random rand = new Random();
        int numServer = rand.nextInt(size);
        return servers.get(numServer);
    }
}
