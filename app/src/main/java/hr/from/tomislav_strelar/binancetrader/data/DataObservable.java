package hr.from.tomislav_strelar.binancetrader.data;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Tomislav on 12.11.2017..
 */

public abstract class DataObservable<T> {

    private final PublishSubject<T> onUpdate;

    protected DataObservable() {
        onUpdate = PublishSubject.create();
    }

    public Observable<T> getObservable() {
        return onUpdate;
    }

    protected void onUpdate(T t) {
        onUpdate.onNext(t);
    }

    public void onError(Throwable t) {
        onUpdate.onError(t);
    }

    public void onComplete() {
        onUpdate.onComplete();
    }
}
