package com.jarwol.wheredweeat.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.jarwol.wheredweeat.R;
import com.jarwol.wheredweeat.data.DatabaseHelper;
import com.jarwol.wheredweeat.models.Visit;

public class NewVisitActivity extends OrmLiteBaseActivity<DatabaseHelper> {
	private static final String[] CONTACT_COLUMNS = new String[] { ContactsContract.Contacts.DISPLAY_NAME, BaseColumns._ID };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_visit);
		// Show the Up button in the action bar.
		setupActionBar();
		populateContactSearch();
	}

	private void populateContactSearch() {
		List<String> contacts = new ArrayList<String>();
		ContentResolver cr = this.getContentResolver();
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, CONTACT_COLUMNS, null, null, null);
		while (cursor.moveToNext()) {
			String name = cursor.getString(0);
			contacts.add(name);
		}
		cursor.close();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.contact_list_item, contacts.toArray(new String[] {}));
		MultiAutoCompleteTextView txtFriends = (MultiAutoCompleteTextView) findViewById(R.id.newVisitFriends);
		txtFriends.setAdapter(adapter);
		txtFriends.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}

	@SuppressWarnings("unused")
    public void addNewActivity(View view) {
		EditText placeName = (EditText) findViewById(R.id.newVisitPlace);
		DatePicker date = (DatePicker) findViewById(R.id.newVisitDate);
		RatingBar rating = (RatingBar) findViewById(R.id.newVisitRating);
		Visit visit = new Visit();
		visit.setDate(new GregorianCalendar(date.getYear(), date.getMonth(), date.getDayOfMonth()).getTime());
		visit.setName(placeName.getText().toString());
		visit.setRating(rating.getRating());
		try {
			Dao<Visit, Integer> dao = this.getHelper().getVisitDao();
			dao.create(visit);
		}
		catch (SQLException e) {
			Log.e(NewVisitActivity.class.getName(), "Error adding new visit", e);
		}
		this.finish();
	}

	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_visit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
