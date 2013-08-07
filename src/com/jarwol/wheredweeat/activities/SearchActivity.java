package com.jarwol.wheredweeat.activities;

import java.sql.SQLException;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.jarwol.wheredweeat.R;
import com.jarwol.wheredweeat.data.DatabaseHelper;
import com.jarwol.wheredweeat.models.Visit;

public class SearchActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		populateVisitList();
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

	private void populateVisitList() {
		ListView list = (ListView) findViewById(R.id.listVisits);
		try {
			List<Visit> visits = this.getHelper().getVisitDao().queryForAll();
			list.setAdapter(new ArrayAdapter<Visit>(this, R.layout.visit_list_item, visits.toArray(new Visit[] {})));
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
