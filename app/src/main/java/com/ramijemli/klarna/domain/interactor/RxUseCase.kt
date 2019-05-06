package com.ramijemli.klarna.domain.interactor

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class RxUseCase<T> {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildUseCase(): Any

    fun execute(observer: DisposableSingleObserver<T>) {
        val single = (this.buildUseCase() as Single<T>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(single.subscribeWith(observer))
    }

    fun execute(observer: DisposableCompletableObserver) {
        val completable = (this.buildUseCase() as Completable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(completable.subscribeWith(observer))
    }

    fun execute(observer: DisposableObserver<T>) {
        val observable = (this.buildUseCase() as Observable<T>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(consumer: Consumer<T>) {
        val flowable = (this.buildUseCase() as Flowable<T>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(flowable.subscribe(consumer))
    }

    fun execute(observer: DisposableMaybeObserver<T>) {
        val maybe = (this.buildUseCase() as Maybe<T>)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(maybe.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
