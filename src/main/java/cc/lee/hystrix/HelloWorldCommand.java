package cc.lee.hystrix;

import com.google.common.base.Preconditions;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.commons.lang.time.FastDateFormat;
import rx.Observable;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class HelloWorldCommand extends HystrixCommand<String> {
    private final String name;

    public HelloWorldCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        Preconditions.checkNotNull(name);

        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        return "Hello world !";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Date timestamp = new Date(1491836800188L);
        System.out.println(FastDateFormat.getInstance().format(timestamp));


        String s = new HelloWorldCommand("lizhitao1").execute();
        System.out.println(s);

        Future<String> s1 = new HelloWorldCommand("lizhitao2").queue();
        System.out.println(s1.get());

        Observable<String> s2 = new HelloWorldCommand("lizhitao3").observe();
        String s22 = s2.toBlocking().single();
        System.out.println(s22);

        String fallback = new HelloWorldCommand(null).execute();
        System.out.println(fallback);
    }
}
