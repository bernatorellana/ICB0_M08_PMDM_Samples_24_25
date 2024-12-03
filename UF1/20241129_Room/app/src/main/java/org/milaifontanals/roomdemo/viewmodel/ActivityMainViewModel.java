package org.milaifontanals.roomdemo.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import org.milaifontanals.roomdemo.db.BestiaryDAO;
import org.milaifontanals.roomdemo.db.TheWitcherDB;
import org.milaifontanals.roomdemo.db.model.Category;
import org.milaifontanals.roomdemo.db.model.Monster;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ActivityMainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Monster>> monsters = new MutableLiveData<>();
    private TheWitcherDB db;
    public ActivityMainViewModel(@NonNull Application application) {
        super(application);



        db = Room.databaseBuilder(
                this.getApplication(),
                TheWitcherDB.class, "database-witcher").build();

        initDB();
    }


    public void initDB(){

        Callable<Boolean> callable = () -> {

            Log.d("XXX", "Abans de fer la consulta");

            int count = db.bestiaryDAO().getMonstersCount();

            Log.d("XXX", "Ciount val:"+count);
            if(count==0) {

                Category category = new Category();
                category.name="Serpentics";
                category .description="Serpents and Co.";
                category.id = (int) db.bestiaryDAO().insertCategory(category);

                Monster golem = new Monster();
                golem.name = "Golem";
                golem.description = "Un golem";
                golem.categoryId = category.id;
                db.bestiaryDAO().insertMonster(golem);

                Monster crystallineSerpent = new Monster();
                crystallineSerpent.name = "Crystalline Serpent";
                crystallineSerpent.description = "A serpentine creature formed from shimmering crystals...";
                crystallineSerpent.categoryId = category.id;
                db.bestiaryDAO().insertMonster(crystallineSerpent);
            }
            
            List<Monster> monstersFromDb = db.bestiaryDAO().getAllSync();
            monsters.postValue(monstersFromDb);

            return true;
        };
        Observable<Boolean> o = Observable.fromCallable(callable);

        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (success) -> {
                            //this.monsters.setValue(queryResult);
                        },
                        error -> {
                            Log.e("XXX", "Error query", error);
                            //this.monsters.setValue(null);
                        }

                );

    }
    private void consultaMonstres() {

        BestiaryDAO bestiaryDAO = db.bestiaryDAO();
        bestiaryDAO.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (queryResult) -> {
                            this.monsters.setValue(queryResult);
                        },
                        error -> {
                            this.monsters.setValue(null);
                        }

                );
    }

    public MutableLiveData<List<Monster>> getMonsters() {
        return monsters;
    }


}
