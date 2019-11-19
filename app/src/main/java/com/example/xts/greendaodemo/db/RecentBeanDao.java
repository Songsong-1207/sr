package com.example.xts.greendaodemo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.exercise2.bean.RecentBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RECENT_BEAN".
*/
public class RecentBeanDao extends AbstractDao<RecentBean, Long> {

    public static final String TABLENAME = "RECENT_BEAN";

    /**
     * Properties of entity RecentBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property News_id = new Property(0, Long.class, "news_id", true, "_id");
        public final static Property Url = new Property(1, String.class, "url", false, "URL");
        public final static Property Thumbnail = new Property(2, String.class, "thumbnail", false, "THUMBNAIL");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
    }


    public RecentBeanDao(DaoConfig config) {
        super(config);
    }
    
    public RecentBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RECENT_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: news_id
                "\"URL\" TEXT," + // 1: url
                "\"THUMBNAIL\" TEXT," + // 2: thumbnail
                "\"TITLE\" TEXT);"); // 3: title
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RECENT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RecentBean entity) {
        stmt.clearBindings();
 
        Long news_id = entity.getNews_id();
        if (news_id != null) {
            stmt.bindLong(1, news_id);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(2, url);
        }
 
        String thumbnail = entity.getThumbnail();
        if (thumbnail != null) {
            stmt.bindString(3, thumbnail);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RecentBean entity) {
        stmt.clearBindings();
 
        Long news_id = entity.getNews_id();
        if (news_id != null) {
            stmt.bindLong(1, news_id);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(2, url);
        }
 
        String thumbnail = entity.getThumbnail();
        if (thumbnail != null) {
            stmt.bindString(3, thumbnail);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RecentBean readEntity(Cursor cursor, int offset) {
        RecentBean entity = new RecentBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // news_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // url
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // thumbnail
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // title
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RecentBean entity, int offset) {
        entity.setNews_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setThumbnail(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RecentBean entity, long rowId) {
        entity.setNews_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RecentBean entity) {
        if(entity != null) {
            return entity.getNews_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RecentBean entity) {
        return entity.getNews_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
