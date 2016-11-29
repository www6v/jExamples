
    package csv;   
      
    import java.io.IOException;   
    import java.nio.charset.Charset;   
    import java.util.ArrayList;   
    import java.util.List;   
      
    import com.csvreader.CsvReader;   
    import com.csvreader.CsvWriter;   
//    import com.syc.test.DAO.ConnectionDB;   
//    import com.syc.test.bean.ReslutBean;   
      
    public class Java2CSV {   
        /**  
         * @param args  
         * @throws Exception  
         */  
        public static void main(String[] args) throws Exception {   
            // 从获取将要写入csv文件的结果集   
            List<ReslutBean> list = new ArrayList<ReslutBean>();   
//            list = ConnectionDB.querySQL();   
            ReslutBean rb1 = new ReslutBean(); 
            rb1.setHelp_keyword_id("k1");
            rb1.setName("name1");
            
            ReslutBean rb2 = new ReslutBean(); 
            rb2.setHelp_keyword_id("k2");
            rb2.setName("name2");
            
            list.add(rb1);
            list.add(rb2);
            
            // 预组装csv文件内容标题行   
            String[][] data = new String[list.size() + 1][2];   
            data[0][0] = "Help_keyword_id";   
            data[0][1] = "Name";   
      
            // 预组装csv文件内容   
            int len = list.size();   
            for (int i = 0; i < len; i++) {   
                data[i + 1][0] = list.get(i).getHelp_keyword_id();   
                data[i + 1][1] = list.get(i).getName();   
            }   
      
            //writerCsv("d://c测试.csv", data);   
            //readerCsv("d://c测试.csv");
            
            readerCsv("D://work//staging(南汇迁移到金桥)/nh2jq.csv");            
        }   
      
        /**  
         * 读取csv  
         *   
         * @param csvFilePath  
         * @throws Exception  
         */  
        public static void readerCsv(String csvFilePath) throws Exception {   
      
            CsvReader reader = new CsvReader(csvFilePath, ',',   
                    Charset.forName("GBK"));// shift_jis日语字体,utf-8  
            reader.readHeaders();   
            String[] headers = reader.getHeaders();   
      
            List<Object[]> list = new ArrayList<Object[]>();   
            while (reader.readRecord()) {   
                list.add(reader.getValues());   
            }   
            Object[][] datas = new String[list.size()][];   
            for (int i = 0; i < list.size(); i++) {   
                datas[i] = list.get(i);   
            }   
      
      
            for (int i = 0; i < headers.length; i++) {   
                System.out.print(headers[i] + "\t");   
            }   
            System.out.println("");   
      
            for (int i = 0; i < datas.length; i++) {   
                Object[] data = datas[i]; // 取出一组数据  
                for (int j = 0; j < data.length; j++) {   
                    Object cell = data[j];   
                    System.out.print(cell + "\t");   
                }   
                System.out.println("");   
            }   
        }   
      
        /**  
         * 写入csv  
         *   
         * @param csvFilePath文件名路径  
         *            +文件名字  
         * @param data数据项  
         */  
        public static void writerCsv(String csvFilePath, String[][] data) {   
      
            CsvWriter writer = null;   
            try {   
                writer = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));// shift_jis日语字体,utf-8  
      
                for (int i = 0; i < data.length; i++) {   
                    writer.writeRecord(data[i]);   
                }   
            } catch (IOException e) {   
                e.printStackTrace();   
            } finally {   
                writer.close();   
            }   
        }   
    }  