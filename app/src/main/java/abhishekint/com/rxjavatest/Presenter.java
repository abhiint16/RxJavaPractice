package abhishekint.com.rxjavatest;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class Presenter implements PresenterInterface {
    Maininterface maininterface;
    List<String> sta = new ArrayList<>();

    public Presenter(Maininterface maininterface) {
        this.maininterface = maininterface;
        sta.add("Beautiful City");
        sta.add("beautiful Country");
        sta.add("Beautiful world");
        sta.add("Beautiful Universe");
        sta.add("Beautiful Multiverse");
    }

    @Override
    public void simpleRx() {
        Log.e("Inside simplerx", "inside simplerx");
        Observable<String> initStaStream = Observable.just("Beautiful City", "beautiful Country", "Beautiful world",
                "Beautiful Universe", "Beautiful Multiverse");
        Flowable<String> initStaStream2 = Flowable.just("Beautiful City", "beautiful Country", "Beautiful world",
                "Beautiful Universe", "Beautiful Multiverse");
        Single<String> single = Single.just("aaa");
        Maybe<String> maybe = Maybe.just("aaa");
        /*Completable completable=new Completable() {
            @Override
            protected void subscribeActual(CompletableObserver s) {

            }
        }*/

        Observable.concat(initStaStream, single.toObservable(), maybe.toObservable(), initStaStream2.toObservable())
                .delay(3000, TimeUnit.MILLISECONDS)          //this delays the entire operation by given time
                /*.concatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just(s).delay(1000,TimeUnit.MILLISECONDS);
                    }
                })*/
                .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS), new BiFunction<String, Long, Observable<String>>() {
                    @Override
                    public Observable<String> apply(String s, Long aLong) throws Exception {
                        return Observable.just(s).delay(aLong,TimeUnit.MILLISECONDS).concat;
                    }
                })
                /*.flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just(s).delay(1000,TimeUnit.MILLISECONDS);
                    }
                })*/
                /*.doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Observable.just(s).delay(1000,TimeUnit.MILLISECONDS);
                    }
                })*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                        maininterface.showToast(s);
                        maininterface.updateText(s);
                    }
                });


        /*Observable<String> initStaStream2=Observable.create(s -> {s.onNext("Beautiful City");});*/
        /*Observable.merge(initStaStream,initStaStream2)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return null;
                    }
                })    //difference bw concat and merge?
                .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS), zipperMethod())
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.take(10).delay(2000, TimeUnit.MILLISECONDS);
                    }
                })
                *//*.range(1,10)*//*
         *//*.zipWith(Observable.interval(2000,TimeUnit.MILLISECONDS).take(initStaStream.count())*//*
                .delay(2000, TimeUnit.MILLISECONDS)       //delay bydeafult run on computation thread
                *//*.observeOn(AndroidSchedulers.mainThread())*//*
         *//* .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s+"mine";
                    }
                })*//*
         *//*.filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.contains("mmmm");
                    }
                })*//*
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Exception {
                        return new JSONObject().put("status",404).toString();
                    }
                })
        .subscribe(new );
                *//*.subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("inside onNext","inside onNExt");
                        maininterface.updateText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("inside onerror","inside onerror");
                        maininterface.updateText(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("inside oncomplete","inside oncomplete");
                    }
                });*//*
         *//*.filter(new Predicate<List<String>>() {
                    @Override
                    public boolean test(List<String> strings) throws Exception {
                        return strings.u;
                    }
                });*/

    }

    private BiFunction<? super String, ? super Long, ?> zipperMethod() {
        return new BiFunction<String, Long, Observable<String>>() {
            @Override
            public Observable<String> apply(String s, Long aLong) throws Exception {
                return Observable.just(s).delay(aLong, TimeUnit.MILLISECONDS);
            }
        };
    }

}

