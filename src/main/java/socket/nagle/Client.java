    package socket.nagle;  
      
    import java.io.*;  
    import java.net.*;  
    import org.apache.log4j.Logger;  
      
    public class Client {  
        private static Logger logger = Logger.getLogger(Client.class);  
        public static void main(String[] args) throws Exception {  
            // 是否分开写head和body  
            boolean writeSplit = true;  //false 
            String host = "localhost";  
            logger.debug("WriteSplit:" + writeSplit);  
            System.out.println("WriteSplit:" + writeSplit);
      
            Socket socket = new Socket();  
            socket.setTcpNoDelay(false); // true   
            socket.connect(new InetSocketAddress(host, 10000));  
      
            InputStream in = socket.getInputStream();  
            OutputStream out = socket.getOutputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
      
            String head = "hello ";  
            String body = "world\r\n";  
            for (int i = 0; i < 10; i++) {  
                long label = System.currentTimeMillis();  
                if (writeSplit) {  
                    out.write(head.getBytes());  
                    out.write(body.getBytes());  
                } else {  
                    out.write((head + body).getBytes());  
                }  
                String line = reader.readLine();  
                logger.debug("RTT:" + (System.currentTimeMillis() - label) + ", receive: " + line);  
                System.out.println("RTT:" + (System.currentTimeMillis() - label) + ", receive: " + line);    
            }  
            in.close();  
            out.close();  
            socket.close();  
        }  
    }  