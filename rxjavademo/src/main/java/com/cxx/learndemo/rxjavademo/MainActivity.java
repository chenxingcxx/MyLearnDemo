package com.cxx.learndemo.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Disposable mDisposable;
    private TextView tvCountDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCountDown = findViewById(R.id.tv_count_down);
        tvCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerCount();
            }
        });
        //timerCount();

        schedulersSwitch2();
    }

    private void schedulersSwitch2() {
//        List<String> strings = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            strings.add("第" + i);
//        }
//        //按顺序显示数据
//        Observable<String> artistBeanObservable = Observable.fromIterable(strings);
//        //延时发射数据 切换歌星数据
//        Observable<Long> interval = Observable.intervalRange(1, 6, 0, 2, TimeUnit.SECONDS);
//
//        Observable.zip(artistBeanObservable, interval, new BiFunction<String, Long, String>() {
//            @Override
//            public String apply(String artistBean, Long aLong) {
//                Log.i(TAG, "zip:" + Thread.currentThread().getName());
//                return artistBean;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ArtistBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        artistBeansDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(ArtistBean artistBean) {
//                        switchArtist(artistBean);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        EvLog.d("PerformPage", "onComplete");
//                        if (artistBeansDisposable != null && !artistBeansDisposable.isDisposed()) {
//                            artistBeansDisposable.dispose();
//                        }
//                        //循环分发数据
//                        DistributeData(false);
//                    }
//                });
    }


    private void schedulersSwitch() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        mDisposable = Observable.just(1)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "map-1:" + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "map-2:" + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "map-3:" + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.i(TAG, "subscribe:" + Thread.currentThread().getName());
                    }
                });
    }

    private void timerCount() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        // 倒计时 10s
        mDisposable = Observable.intervalRange(0, 11, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "倒计时" + aLong);

                        tvCountDown.setText("倒计时 " + String.valueOf(10 - aLong) + " 秒");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "倒计时完毕");

                        Toast.makeText(MainActivity.this, "倒计时完毕", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
