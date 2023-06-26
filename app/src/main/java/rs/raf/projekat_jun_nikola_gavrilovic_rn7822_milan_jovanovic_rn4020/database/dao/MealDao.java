package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.MealEntity;

@Dao
public interface MealDao {
    @Query("SELECT * FROM MealEntity")
    List<MealEntity> findAll();

    @Query("SELECT * FROM MealEntity WHERE id = :id")
    MealEntity find(int id);

    @Query("SELECT * FROM MealEntity WHERE date_saved >= :startDate order by date_saved asc")
    List<MealEntity> getMealsWithinLast7Days(Date startDate);


    @Update
    void update(MealEntity user);

    @Insert
    void add(MealEntity user);

    @Delete
    void delete(MealEntity user);
}
