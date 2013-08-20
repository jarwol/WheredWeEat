package com.jarwol.wheredweeat.ui;

import java.sql.SQLException;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.jarwol.wheredweeat.R;
import com.jarwol.wheredweeat.data.DatabaseHelper;
import com.jarwol.wheredweeat.models.Visit;

public class SearchActivity extends OrmLiteBaseActivity<DatabaseHelper> {
	private SparseArray<Visit> visits;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		this.visits = new SparseArray<Visit>();
		populateVisits();

		final ListView list = (ListView) findViewById(R.id.listVisits);
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, final View v, int pos, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
				builder.setMessage("Are you sure you want to delete this visit?").setTitle("Delete Visit");
				builder.setNegativeButton("Cancel", null);
				builder.setPositiveButton("Delete", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							int key = ((Integer) v.getTag()).intValue();
							Visit visit = SearchActivity.this.visits.get(key);
							SearchActivity.this.visits.delete(key);
							SearchActivity.this.getHelper().getVisitDao().delete(visit);
							((VisitAdapter) list.getAdapter()).remove(visit);
						}
						catch (SQLException e) {
							Log.e(SearchActivity.class.getName(), "Error deleting visit", e);
						}
					}
				});
				builder.show();
				return true;
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateVisits();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_new_visit:
				this.showAddNewVisit();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void populateVisits() {
		ListView list = (ListView) findViewById(R.id.listVisits);
		try {
			List<Visit> visitList = this.getHelper().getVisitDao().queryBuilder().orderBy("date", false).query();
			list.setAdapter(new VisitAdapter(this, R.layout.visit_list_item, visitList));
			for (Visit v : visitList) {
				this.visits.put(v.getId(), v);
			}
		}
		catch (SQLException e) {
			Log.e(SearchActivity.class.getName(), "Error retrieving visits list", e);
		}
	}

	public void showAddNewVisit() {
		Intent intent = new Intent(this, NewVisitActivity.class);
		this.startActivity(intent);
	}
}
