package org.milaifontanals.magicthegathering;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.milaifontanals.magicthegathering.model.Card;
import org.milaifontanals.magicthegathering.model.Example;
import org.milaifontanals.magicthegathering.utils.NetworkUtils;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Callable<List<Card>> callable = () -> {
            // Perform some task here

            String json = NetworkUtils.downloadResource("https://api.magicthegathering.io/v1/cards");

            Gson gson = new Gson();
            Example e = gson.fromJson(json, Example.class);
            return e.getCards();
        };

        Observable<List<Card>> observable = Observable.fromCallable(callable);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> { Toast.makeText(
                                MainActivity.this, "Completed:"+result.get(0).getName(),
                                Toast.LENGTH_SHORT).show(); },
                        error -> { Toast.makeText(
                                    this, "Error:"+error,
                                            Toast.LENGTH_SHORT).show(); }

                );

    }
}