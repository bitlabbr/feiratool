package com.recife.bill.feiratool.model.repository.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.recife.bill.feiratool.model.repository.persistence.dao.ShoppListDao;
import com.recife.bill.feiratool.model.repository.persistence.model.Product;
import com.recife.bill.feiratool.model.repository.persistence.model.ShopItem;
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppItemEntry;
import com.recife.bill.feiratool.model.repository.persistence.model.ShoppList;
import com.recife.bill.feiratool.model.utils.AirPowerLog;

@Database(entities = {
        ShoppItemEntry.class, ShoppList.class, Product.class}, version = 5, exportSchema = false)
public abstract class AirPowerDatabase extends RoomDatabase {

    public static final String TAG = AirPowerDatabase.class.getSimpleName();
    public static final String DATABASE_NAME = "AirPowerApp.db";
    private static AirPowerDatabase dbInstance;

    public static synchronized AirPowerDatabase getDataBaseInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context, AirPowerDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration() // TODO remove before release
                    .build();
            if (AirPowerLog.ISLOGABLE) AirPowerLog.d(TAG, "create instance");
        }
        return dbInstance;
    }

    public abstract ShoppListDao shoppListDao();
}
