package netty;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleClient {

//    private static final Logger logger = LoggerFactory.getLogger(SimpleClient.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                    }
                });
        b.connect("localhost", 8090).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
//                    future.channel().close();
                    future.channel().write(Unpooled.buffer().writeBytes("123".getBytes())).addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (!future.isSuccess()) {
//                                logger.error("Error", future.cause());
                            	System.out.println(future.cause());
                            }
                            
                            if (future.isSuccess()) {
//                              logger.error("Error", future.cause());
                          	System.out.println("success");
                          }
                        }
                    });
                    future.channel().flush();
                }
            }
        });
    }
}
