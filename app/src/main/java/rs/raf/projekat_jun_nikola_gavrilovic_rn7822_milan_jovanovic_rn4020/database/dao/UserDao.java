package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    List<UserEntity> findAll();

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    UserEntity find(int id);

    @Query("SELECT * FROM UserEntity WHERE username = :username")
    UserEntity findByUsername(String username);

    @Update
    void update(UserEntity user);

    @Insert
    void add(UserEntity user);

    @Delete
    void delete(UserEntity user);
}
