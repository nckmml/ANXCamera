package com.android.camera.db.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.android.camera.log.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;
    /* access modifiers changed from: private */
    public static final String TAG = DaoMaster.class.getSimpleName();

    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        public void onUpgrade(Database database, int i, int i2) {
            String access$000 = DaoMaster.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Upgrading schema from version ");
            sb.append(i);
            sb.append(" to ");
            sb.append(i2);
            sb.append(" by dropping all tables");
            Log.i(access$000, sb.toString());
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }

    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 1);
        }

        public OpenHelper(Context context, String str, CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 1);
        }

        public void onCreate(Database database) {
            Log.i(DaoMaster.TAG, "Creating tables for schema version 1");
            DaoMaster.createAllTables(database, false);
        }
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this((Database) new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 1);
        registerDaoClass(SaveTaskDao.class);
    }

    public static void createAllTables(Database database, boolean z) {
        SaveTaskDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        SaveTaskDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
