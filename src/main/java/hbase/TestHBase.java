package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestHBase {
        //声明一个configuration对象
                private Configuration conf = null;
        //声明一个Hbase数据库连接
                private Connection conn = null;
   
                @Before
        public void init() throws IOException {
                   conf = HBaseConfiguration.create();
                   //对于hbase的客户端，只需要知道hbase所使用的zookeeper集群地址就可以了
//                   conf.set("hbase.zookeeper.quorum","node :    ,node :    ,node :    ");

                    conf.set("hbase.zookeeper.quorum","lb.tuhu.work:2181");
//                    conf.set("hbase.zookeeper.quorum","10.100.141.219:2181");

//                    System.setProperty("hadoop.home.dir", "hdfs://work-arch-kv-redis-1:8020/hbase");

                   //获取链接
                   conn = ConnectionFactory.createConnection(conf);
//                    createTable();
               }


        /////
                @Test
        public void createTable() throws IOException {
                   //获取一个表的管理器
                   Admin admin = conn.getAdmin();
                   //表描述器，并指定表名
                   HTableDescriptor table = new HTableDescriptor(TableName.valueOf("t_user_info".getBytes()));
                   //列族描述器，指定列族名
                   HColumnDescriptor hcd  = new HColumnDescriptor("base_info");
                   //指定第二个描述器，指定列名
//                   HColumnDescriptor hcd  = new HColumnDescriptor("extra_info");
                   //版本shul
                   hcd .setVersions( 1, 2 );

                   //将列族描述器添加到表描述器中
                   table.addFamily(hcd );
                   //利用表的管理器创建表
                   admin.createTable(table);

                   //关闭
                   admin.close();
                   conn.close();
               }


        //////
                @Test
        public void modifyTable() throws IOException {
                   Admin admin = conn.getAdmin();
                   HTableDescriptor table = admin.getTableDescriptor(TableName.valueOf("t_user_info"));
          
                   HColumnDescriptor hcd  = table.getFamily("extra_info".getBytes());
                   hcd.setVersions(2, 3);

                   table.addFamily(new HColumnDescriptor("other_info"));

                   admin.modifyTable(TableName.valueOf("t_user_info"), table);
                   //关闭
                   admin.close();
                   conn.close();
               }
   
        /////
                @Test
        public void testPut() throws IOException {
                   //构建一个table对象，通过table对象来添加数据
                   Table table = conn.getTable(TableName.valueOf("t_user_info"));
                   //构建一个集合，用于存放put对象
                   ArrayList<Put> puts = new ArrayList<Put>();
                   //构建一个put对象kv,指定其行键 例如hbase shell:put '表名','rowkey','列族：列名称','值'
                   Put put   = new Put(Bytes.toBytes("user"));
                   put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhangsan"));
          
//                   Put put   = new Put("user   ".getBytes());
//                   put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("password"),Bytes.toBytes("      "));
//
//                   Put put   = new Put("user   ".getBytes());
//                   put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("lisi"));
//                   put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("password"), Bytes.toBytes("     "));
//                   put  .addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
//
//                    Put put   = new Put("zhang_sh_  ".getBytes());
//                    put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang  "));
//                    put  .addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
//
//                    Put put   = new Put("zhang_sh_  ".getBytes());
//                    put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang  "));
//                    put  .addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
//
//                    Put put   = new Put("liu_sh_  ".getBytes());
//                    put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("liu  "));
//                    put  .addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
//
//                    Put put   = new Put("zhang_bj_  ".getBytes());
//                    put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang  "));
//                    put  .addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
//
//                    Put put   = new Put("zhang_bj_  ".getBytes());
//                    put  .addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang  "));
//                    put  .addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
//                    put  .setDurability(Durability.SYNC_WAL);
//
                    puts.add(put  );
//                    puts.add(put  );
//                    puts.add(put  );
//                    puts.add(put  );
//                    puts.add(put  );
//                    puts.add(put  );
//                    puts.add(put  );
//                    puts.add(put  );
           
                    table.put(puts);
                    table.close();
                    conn.close();
                }
   

        /////
                @Test
        public void testGet() throws IOException {
                    Table table = conn.getTable(TableName.valueOf("t_user_info"));
                    //构建一个get查询参数对象，指定要get的哪一行
                    Get get = new Get("user".getBytes());
                    //返回查询结果数据
                    Result result = table.get(get);
                    //获取结果中的所有cell
                    List<Cell> cells = result.listCells();
                    for (Cell c:cells) {
                            //获取行键
                            byte[] rowBytes = CellUtil.cloneRow(c);
                            //获取列族
                            byte[] familyBytes = CellUtil.cloneFamily(c);
                            //获取列族下的列名称
                            byte[] qualifierBytes = CellUtil.cloneQualifier(c);
                            byte[] valueBytes = CellUtil.cloneValue(c);
               
                            System.out.print(new String(rowBytes) + " ");
                            System.out.print(new String(familyBytes) + ":");
                            System.out.print(new String(qualifierBytes) + " ");
                            System.out.println(new String(valueBytes));
                        }
                    //关闭
                    table.close();
                    conn.close();
                }
   
                /**
          * scan批量查询数据
          * @throws IOException
          */
                @Test
        public void testScan() throws IOException {
                    //获取table对象
                    Table table = conn.getTable(TableName.valueOf("t_user_info"));
                    //获取scan对象
                    Scan scan = new Scan();
                    //获取查询的数据
                    ResultScanner scanner = table.getScanner(scan);
                    //获取resultscanner所有数据，返回迭代器
                    Iterator<Result> iter = scanner.iterator();
                    //遍历迭代器
                    while (iter.hasNext()){
                            //获取当前每一行结果数据
                            Result result = iter.next();
                            //获取当前每一行所有cell对象
                            List<Cell> cells = result.listCells();
                            //迭代所有cell
                            for (Cell c:cells) {
                                    //获取行键
                                    byte[] rowBytes = CellUtil.cloneRow(c);
                                    //获取列族
                                    byte[] familyBytes = CellUtil.cloneFamily(c);
                                    //获取列族下的列名称
                                    byte[] qualifierBytes = CellUtil.cloneQualifier(c);
                                    //列字段的值
                                    byte[] valueBytes = CellUtil.cloneValue(c);
                   
                                    System.out.print(new String(rowBytes) + " ");
                                    System.out.print(new String(familyBytes) + ":");
                                    System.out.print(new String(qualifierBytes) + " ");
                                    System.out.println(new String(valueBytes));
                                }
                            System.out.println("--------------------------------------------------------");
                        }
                    //关闭
                    table.close();
                    conn.close();
                }
   
                /**
          * 删除表中的列数据
          * @throws IOException
          */
                @Test
        public void testDelData() throws IOException {
                    //获取table对象
                    Table table = conn.getTable(TableName.valueOf("t_user_info"));
                    //获取delete对象，需要一个rowkey
                    Delete delete = new Delete("user   ".getBytes());
           
                    //在delete对象中指定要删除的列族-列名称
                    delete.addColumn("base_info".getBytes(),"password".getBytes());
                    //执行删除
                    table.delete(delete);
                    //关闭
                    table.close();
                    conn.close();
                }
   
                /**
          * 删除表
          * disable 't_user_info'
          * drop 't_user_info'
          * @throws IOException
          */
                @Test
        public void testDropTable() throws IOException {
                    Admin admin = conn.getAdmin();
                    //删除表时先需要disable,将表置为不可用，然后delete
                    admin.disableTable(TableName.valueOf("t_user_info"));
                    admin.deleteTable(TableName.valueOf("t_user_ifo"));
                    admin.close();
                    conn.close();
                }
   
                /**
          * 行键 前缀过滤器
          * @throws IOException
          */
                @Test
        public void testPreFixFilter() throws IOException {
                    //针对于行键的前缀过滤器
                    Filter filter = new PrefixFilter(Bytes.toBytes("liu"));
                    testScan(filter);
                }
   
                /**
          * 行过滤器
          * 行键值 < user     的所有行
          * @throws IOException
          */
                @Test
        public void testRowFilter() throws IOException {
                    //行过滤器  需要一个比较运算符和比较器
                    // 行键值 < user     的行值
                    RowFilter rf  = new RowFilter(CompareFilter.CompareOp.LESS, new BinaryComparator(Bytes.toBytes("user   ")));
                    testScan(rf);
                }
   
                /**
          * 列族过滤器
          * 满足列族的所有行的数据，但是只是返回行中指定列族的所有列
          * @throws IOException
          */
                @Test
        public void testFamilyFilter() throws IOException {
                    //列族名称的过滤器  返回结果中只会包含满足条件的列族中的数据
                    FamilyFilter ff  = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("base_info")));
           
//                    FamilyFilter ff  = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("base")));
                    testScan(ff );
                }
   
                /**
          * 列名过滤器
          * 满足列的所有行的数据，但是只是返回行中指定列
          * @throws IOException
          */
                @Test
        public void testQualifierFilter() throws IOException {
                    //针对列名的过滤器  返回结果中只包含满足条件的列的数据
                    QualifierFilter qf  = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("password")));
           
//                    QualifierFilter qf  = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("user")));
                    testScan(qf );
                }
   
                /**
          * 列值过滤器
          * 满足列值的所有行的，所有列数据
          * @throws IOException
          */
                @Test
        public void testColunmValueFilter() throws IOException {
                    //针对指定一个列的value的比较器过滤
                    ByteArrayComparable comparator  = new RegexStringComparator("^zhang");  //以zhang开头
           
//                    ByteArrayComparable comparator  = new SubstringComparator("si");  //包含“si”字串
                    //列族，列名 = 列值
                    SingleColumnValueFilter scvf = new SingleColumnValueFilter("base_info".getBytes(), "username".getBytes(),
                                    CompareFilter.CompareOp.EQUAL, comparator );
                    testScan(scvf);
                }
   
                /**
          * 多个过滤器同时使用
          * @throws IOException
          */
                @Test
        public void testFilterList() throws IOException {
                    //构建一个列族的过滤器
                    FamilyFilter ff  = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("base")));
                    //构建一个列的前缀过滤器
                    ColumnPrefixFilter cpf  = new ColumnPrefixFilter("password".getBytes());
                    //指定多个过滤器是否同时满足条件
            //        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE); //只满足一个过滤条件即可
                    FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL); //满足所有过滤条件
           
                    filterList.addFilter(ff );
                    filterList.addFilter(cpf );
                    testScan(filterList);
                }
   
           
                public void testScan(Filter filter) throws IOException {
                    Table table = conn.getTable(TableName.valueOf("t_user_info"));
           
                    Scan scan = new Scan();
                    //设置过滤器
                    scan.setFilter(filter);
           
                    ResultScanner resultScanner = table.getScanner(scan);
                    Iterator<Result> iterator = resultScanner.iterator();
                    //遍历所有result对象，获取结果
                    while (iterator.hasNext()){
                            Result result = iterator.next();
                            List<Cell> cells = result.listCells();
                            for (Cell c: cells){
                                    //获取行键
                                    byte[] rowBytes = CellUtil.cloneRow(c);
                                    //获取列族
                                    byte[] familyBytes = CellUtil.cloneFamily(c);
                                    //获取列族下的列名称
                                    byte[] qualifierBytes = CellUtil.cloneQualifier(c);
                                    //列字段值
                                    byte[] valueBytes = CellUtil.cloneValue(c);
                   
                                    System.out.print(new String(rowBytes) + " ");
                                    System.out.print(new String(familyBytes) + ":");
                                    System.out.print(new String(qualifierBytes) + " ");
                                    System.out.println(new String(valueBytes));
                                }
                            System.out.println("-------------------------------------------------------");
                        }
                }
    }