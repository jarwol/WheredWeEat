package com.jarwol.wheredweeat.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ConfirmDialogFragment extends DialogFragment {
	private ConfirmDialogListener listener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.listener = (ConfirmDialogListener) activity;
		}
		catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement ConfirmDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = this.getArguments();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(args.getString("msg")).setTitle(args.getString("title")).setPositiveButton(args.getString("posButtonText"), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				ConfirmDialogFragment.this.listener.onDialogPositiveClick(ConfirmDialogFragment.this);
			}
		}).setNegativeButton(args.getString("negButtonText"), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				ConfirmDialogFragment.this.listener.onDialogPositiveClick(ConfirmDialogFragment.this);
			}
		});
		return builder.create();
	}
}
