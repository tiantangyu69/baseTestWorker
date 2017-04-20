package cc.lee.hystrix;

import com.google.common.base.Preconditions;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class ObservableHelloWorldCommand extends HystrixObservableCommand<String> {
    private final String name;

    public ObservableHelloWorldCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        Preconditions.checkNotNull(name);

        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext("Hello ");
                        subscriber.onNext(name + " !");
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext("fallback ");
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public static void main(String[] args) {
        Observable<String> ho = new ObservableHelloWorldCommand("World").observe();

        ho.subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println(s);
            }
        });


        Observable<String> ho1 = new ObservableHelloWorldCommand(null).observe();

        ho1.subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println(s);
            }
        });
    }
}
