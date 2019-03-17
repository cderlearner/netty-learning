import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Author: linjx
 * Date: 2019/3/10
 */
public class Test {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while (true) {
                System.out.println("hello wolrd!!");
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
            }
        });
        t.start();
    }
}
