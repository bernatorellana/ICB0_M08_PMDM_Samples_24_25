package org.milaifontanals.roomdemo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.milaifontanals.roomdemo.db.model.Category;
import org.milaifontanals.roomdemo.db.model.Monster;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface BestiaryDAO {


    @Query("SELECT count(id) FROM monster")
    int getMonstersCount();

    @Query("SELECT * FROM monster")
    Single<List<Monster>> getAll();

    @Query("SELECT * FROM monster")
    List<Monster> getAllSync();

    @Query("SELECT * FROM monster WHERE id = :id")
    Monster getMonsterById(int id);

    @Query("SELECT * FROM monster WHERE name LIKE :name")
    List<Monster> findByName(String name);

    @Delete
    void deleteMonster(Monster monster);

    @Update
    void updateMonster(Monster monster);

    @Insert
    void insertMonster(Monster monster);

    @Insert
    long insertCategory(Category category);
    @Update
    void updateCategory(Category category);
    @Delete
    void deleteCategory(Category category);
    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    @Query("SELECT * FROM category WHERE id = :id")
    Category getCategoryById(int id);

}
