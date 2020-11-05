package data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.CovidData;

@Database(entities = {CovidData.class}, version =1 , exportSchema = false)

public abstract class CovidRoomDatabase extends RoomDatabase {
    public static volatile CovidRoomDatabase INSTANCE;
    public abstract CovidDao covidDao();
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CovidRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CovidRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CovidRoomDatabase.class, "app_database")
                            .addCallback(roomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;

    }
    public static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            CovidDao dao = INSTANCE.covidDao();
                            dao.deleteAll();
                        }
                    });
                }
            };

}
