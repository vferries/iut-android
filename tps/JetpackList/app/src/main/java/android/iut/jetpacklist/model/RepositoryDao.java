package android.iut.jetpacklist.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface RepositoryDao {
    @Query("SELECT * FROM repository")
    LiveData<List<Repository>> getAll();

    @Query("SELECT * FROM repository WHERE name LIKE :name LIMIT 1")
    Repository findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Repository... repositories);

    @Delete
    void delete(Repository repository);
}