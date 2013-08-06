package com.jarwol.wheredweeat.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jarwol.wheredweeat.models.Friend;
import com.jarwol.wheredweeat.models.Item;
import com.jarwol.wheredweeat.models.Visit;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DB_NAME = "wheredweeat.db";
	private static final int DB_VERSION = 1;
	private static final Class<?>[] DB_CLASSES = new Class<?>[] { Friend.class, Item.class, Visit.class };

	private Dao<Visit, Integer> visitDao;
	private Dao<Visit, Integer> friendDao;
	private Dao<Visit, Integer> itemDao;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		try {
			this.visitDao = this.getDao(Visit.class);
			this.itemDao = this.getDao(Item.class);
			this.friendDao = this.getDao(Friend.class);
		}
		catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Error initializing DAOs", e);
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource conn) {
		try {
			for (Class<?> cls : DB_CLASSES) {
				TableUtils.createTable(conn, cls);
			}
		}
		catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Error creating DB tables", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {

	}

	public Dao<Visit, Integer> getVisitDao() {
		return this.visitDao;
	}

	public void setVisitDao(Dao<Visit, Integer> visitDao) {
		this.visitDao = visitDao;
	}

	public Dao<Visit, Integer> getFriendDao() {
		return this.friendDao;
	}

	public void setFriendDao(Dao<Visit, Integer> friendDao) {
		this.friendDao = friendDao;
	}

	public Dao<Visit, Integer> getItemDao() {
		return this.itemDao;
	}

	public void setItemDao(Dao<Visit, Integer> itemDao) {
		this.itemDao = itemDao;
	}
}
