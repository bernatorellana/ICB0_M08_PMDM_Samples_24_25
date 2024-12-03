package org.milaifontanals.roomdemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.milaifontanals.roomdemo.db.model.Category;
import org.milaifontanals.roomdemo.db.model.Monster;

@Database(entities = {Monster.class, Category.class}, version = 1)
public abstract class TheWitcherDB extends RoomDatabase {
    public abstract BestiaryDAO bestiaryDAO();
}
