package javacore.async;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ListenableFutureTest {
    public static void main( String args[]) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture explosion = service.submit(new Callable() {

            public Object call() {
                System.out.println( "call() " );
                return pushBigRedButton();

            }

            public Object pushBigRedButton() {
                  return new Object();
            }
        });

        explosion.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println( "addListener" );
            }
        }, Executors.newFixedThreadPool(10));

        Futures.addCallback(explosion, new FutureCallback() {

            // we want this handler to run immediately after we push the big red button!

            public void onSuccess() {
//                walkAwayFrom(explosion);
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println( "onSuccess" );
            }

            public void onFailure(Throwable thrown) {

//                battleArchNemesis(); // escaped the explosion!
                System.out.println( "onFailure" );
            }
        });
    }
}