package com.jarwol.wheredweeat.ui;

import java.util.List;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jarwol.wheredweeat.R;
import com.jarwol.wheredweeat.models.Visit;

public class VisitAdapter extends ArrayAdapter<Visit> {
	private int resource;

	public VisitAdapter(Context context, int resource, List<Visit> visits) {
		super(context, resource, visits);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(this.resource, null);
		}
		Visit visit = this.getItem(position);
		if (visit != null) {
			view.setTag(Integer.valueOf(visit.getId()));
			TextView place = (TextView) view.findViewById(R.id.placeName);
			TextView date = (TextView) view.findViewById(R.id.visitDate);
			TextView friends = (TextView) view.findViewById(R.id.friends);
			RatingBar rating = (RatingBar) view.findViewById(R.id.visitRating);
			place.setText(visit.getName());
			date.setText(DateUtils.formatDateTime(this.getContext(), visit.getDate().getTime(), DateUtils.FORMAT_SHOW_DATE));
			friends.setText(visit.getFriendString());
			rating.setRating(visit.getRating());
		}
		return view;
	}

}
