    package network.socket.nagle;
      
    import java.io.*;  
    import java.net.*;  
      
    import org.apache.log4j.Logger;  
      
    public class Server {  
        private static Logger logger = Logger.getLogger(Server.class);  
      
        public static void main(String[] args) throws Exception {  
            ServerSocket serverSocket = new ServerSocket();  
            serverSocket.bind(new InetSocketAddress(10000));  
            logger.debug(serverSocket);  
            logger.debug("Server startup at 10000");  
            
            System.out.println(serverSocket);
            System.out.println("Server startup at 10000");
            while (true) {  
                Socket socket = serverSocket.accept();  
                InputStream in = socket.getInputStream();  
                OutputStream out = socket.getOutputStream();  
      
                while (true) {  
                    try {  
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
                        String line = reader.readLine();  
                        logger.debug(line);  System.out.println(line);
                        out.write((line + "\r\n").getBytes());  
                    } catch (Exception e) {  
                        break;  
                    }  
                }  
            }  
        }  
    }  