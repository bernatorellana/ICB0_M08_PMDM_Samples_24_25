package org.milaifontanals.magicthegathering.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.milaifontanals.magicthegathering.api.MagicAPI;
import org.milaifontanals.magicthegathering.api.MagicApiService;
import org.milaifontanals.magicthegathering.model.Card;

import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Card>> cardList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        cardList = new MutableLiveData<>();
        descarregaCartes();
    }

    public LiveData<List<Card>> getCardList() {
        return cardList;
    }

    private void descarregaCartes(){
        if(cardList.getValue()!=null) return;

        /*
        // Versió que fa la descàrrega sense usar retrofit,
        per tant és una descàrrega manual
        //====================================================
        Callable<List<Card>> callable = () -> {
            String json = NetworkUtils.downloadResource("https://api.magicthegathering.io/v1/cards");

            Gson gson = new Gson();
            Example e = gson.fromJson(json, Example.class);
            return e.getCards();
        };
        Observable<List<Card>> observable = Observable.fromCallable(callable);
        */
        // ==================================================================
        // Instanciar retrofit per fer servir les crides a la API de màgic
        MagicAPI service =  MagicApiService.getMagicAPI();

        service.getCards().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> {
                            cardList.setValue(result.getCards());
                        },
                        error -> {
                            cardList.setValue(null);
                        }

                );
    }



}
