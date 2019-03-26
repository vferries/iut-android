package android.iut.jetpacklist.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Repository.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepositoryDao repositoryDao();
}