public class Main {
    public static void main(String[] args) {
        String hostPort = "localhost:2181";
        String znode = "/nodeteste";
        String filename = "teste";
        String exec[] = new String[0];
        try {
            new Executor(hostPort, znode, filename, exec).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
