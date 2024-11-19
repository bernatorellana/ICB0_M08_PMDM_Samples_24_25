package org.milaifontanals.magicthegathering.api;

import org.milaifontanals.magicthegathering.model.CardSet;


import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface MagicAPI {
    @GET("cards")
    Single<CardSet> getCards();

}
