import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperClient {
    private static final String ZOOKEEPER_HOST = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        // Cria uma conexão com o ZooKeeper
        ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_HOST, SESSION_TIMEOUT, null);

        if (zooKeeper.exists("/nodeteste", false) == null) {
            byte[] data = "Hello World!".getBytes();
            zooKeeper.create("/nodeteste", data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // Verifica se o znode existe
        Stat stat = zooKeeper.exists("/nodeteste", false);
        if (stat == null) {
            System.out.println("O znode não existe!");
            return;
        }

        // Atualiza o valor do znode
        String data2 = "Novo valor do znode";
        zooKeeper.setData("/nodeteste", data2.getBytes(), stat.getVersion());

        System.out.println("setou novo valor...");

        // Fecha a conexão com o ZooKeeper
        zooKeeper.close();
    }
}
