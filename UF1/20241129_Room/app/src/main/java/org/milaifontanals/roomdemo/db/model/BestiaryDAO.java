package org.milaifontanals.roomdemo.db.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BestiaryDAO {


    @Query("SELECT * FROM monster")
    List<Monster> getAll();

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
    void insertCategory(Category category);

    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    @Query("SELECT * FROM category WHERE id = :id")
    Category getCategoryById(int id);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);


}
