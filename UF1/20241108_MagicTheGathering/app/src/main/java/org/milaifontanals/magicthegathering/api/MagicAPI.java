package org.milaifontanals.magicthegathering.api;

import org.milaifontanals.magicthegathering.model.CardSet;


import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MagicAPI {
    @GET("cards")
    Single<CardSet> getCards();

    @GET("cards")
    Single<CardSet> getCardsPerColor(@Query("colors") String colors);


    @GET("sections/{sect}.json")
    Single<CardSet> getSection(@Path("sect") String section);


}
