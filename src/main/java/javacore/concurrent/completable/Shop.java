package javacore.concurrent.completable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    Random random = new Random();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop(String name) {
        super();
        this.name = name;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static Table table;
    static {
        Configuration configuration = crateHBaseConfiguration(null);
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(TableName.valueOf("HBaseForTest"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized Configuration crateHBaseConfiguration(String servers) {
        String zkurl = null;
        String zkpath = null;
        if (servers != null && servers.indexOf("/") > 0) {
            int index = servers.indexOf("/");
            zkurl = servers.substring(0, index);
            zkpath = servers.substring(index);
        }

        String hbaseZKQuorum = zkurl;
        String hbaseZKNode = zkpath;
//        if (hbaseZKQuorum == null) {
//            hbaseZKQuorum = ApolloServerConfig.getInstance().getStringValue(HBASE_ZOOKEEPER_QUORUM,
//                    "lb.tuhu.work:2181");
//        }

        hbaseZKQuorum = "lb.tuhu.work:2181";
        if (hbaseZKNode == null) {
            hbaseZKNode = "/hbase";
        }

        Configuration configuration = HBaseConfiguration.create();
        configuration.get("hadoop.home.dir");
        configuration.set("hbase.zookeeper.quorum", hbaseZKQuorum);
        configuration.set("zookeeper.znode.parent", hbaseZKNode);

        return configuration;
    }

    public double getPrice(String product) {
//        delay();

        try {
            StopWatch sw = new StopWatch("test");


            Put put = new Put("finalKey".getBytes());
            put.addColumn("work".getBytes(), "abc".getBytes(), "bytevalue".getBytes());

            sw.start("task1");
            table.put(put);
            sw.stop();


            System.out.println(sw.prettyPrint());
        } catch (IOException e) {

        }

        return random.nextDouble() * 100;
    }

//	public Future<Double> getPriceAsync(String product){
//		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//		new Thread(()->futurePrice.complete(getPrice(product))).start();
//		return futurePrice;
//	}
    /**推荐使用这种方式
     * 1.将getPrice()方法转为异步调用。
     * 2.supplyAsync参数是一个生产者。Supplier 没有参数返回一个对象。若lambda没有参数并且可以返回对象则满足
     * 3.supplyAsync内部对Supplier方法对异常进行处理，避免因为异常程序永久阻塞。
     * */
    public Future<Double> getPriceAsync(String product){
        return CompletableFuture.supplyAsync(()->getPrice(product));
    }

    public static void main(String[] args) throws Exception{
        Shop shop = new Shop("bestShop");
        long start = System.currentTimeMillis();
        Future<Double> futurePrice = shop.getPriceAsync("some product");
        System.out.println("调用返回，耗时"+(System.currentTimeMillis() - start));
        double price = futurePrice.get();
        System.out.println("价格返回，耗时"+(System.currentTimeMillis() - start));
    }
}
