package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseTest {
    public static void main(String args[]) {
        HbaseTest hbaseTest = new HbaseTest();
        try {
            hbaseTest.init();
           hbaseTest.createTable();
            hbaseTest.testPut();
            hbaseTest.testGet();
            hbaseTest.modifyTable();

            hbaseTest.testDelData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Configuration conf = null;
    private Connection conn = null;

    public void init() throws IOException {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","lb.tuhu.work:2181,lb.tuhu.work:2181,lb.tuhu.work:2181");
//                    conf.set("hbase.zookeeper.quorum","10.100.141.219:2181");

        conn = ConnectionFactory.createConnection(conf);
    }


    public void createTable() throws IOException {
        Admin admin = conn.getAdmin();

        HTableDescriptor table = new HTableDescriptor(TableName.valueOf("t_user_info".getBytes()));

        HColumnDescriptor hcd  = new HColumnDescriptor("base_info");
        hcd .setVersions( 1, 2 );
        table.addFamily(hcd );
        admin.createTable(table);

        admin.close();
        conn.close();
    }


    public void testPut() throws IOException {

        Table table = conn.getTable(TableName.valueOf("t_user_info"));

        ArrayList<Put> puts = new ArrayList<Put>();
        Put put   = new Put(Bytes.toBytes("user"));
        put.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhangsan"));

        puts.add(put);

        table.put(puts);
        table.close();
        conn.close();
    }

    public void testGet() throws IOException {
        Table table = conn.getTable(TableName.valueOf("t_user_info"));
        Get get = new Get("user".getBytes());
        Result result = table.get(get);
        List<Cell> cells = result.listCells();
        for (Cell c:cells) {
            byte[] rowBytes = CellUtil.cloneRow(c);
            byte[] familyBytes = CellUtil.cloneFamily(c);
            byte[] qualifierBytes = CellUtil.cloneQualifier(c);
            byte[] valueBytes = CellUtil.cloneValue(c);

            System.out.print(new String(rowBytes) + " ");
            System.out.print(new String(familyBytes) + ":");
            System.out.print(new String(qualifierBytes) + " ");
            System.out.println(new String(valueBytes));
        }
        table.close();
        conn.close();
    }


    public void modifyTable() throws IOException {
        Admin admin = conn.getAdmin();
        HTableDescriptor table = admin.getTableDescriptor(TableName.valueOf("t_user_info"));

//        HColumnDescriptor hcd  = table.getFamily("extra_info".getBytes());
//        hcd.setVersions(2, 3);

        table.addFamily(new HColumnDescriptor("other_info"));

        admin.modifyTable(TableName.valueOf("t_user_info"), table);

        admin.close();
        conn.close();
    }

    @Test
    public void testDelData() throws IOException {
        Table table = conn.getTable(TableName.valueOf("t_user_info"));
        Delete delete = new Delete("user".getBytes());

//        delete.addColumn("base_info".getBytes(),"password".getBytes());

        delete.addColumn("base_info".getBytes(),"username".getBytes());

        table.delete(delete);

        table.close();
        conn.close();
    }
}


