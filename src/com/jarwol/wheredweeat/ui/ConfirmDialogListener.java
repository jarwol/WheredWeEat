package com.jarwol.wheredweeat.ui;

import android.app.DialogFragment;

public interface ConfirmDialogListener {
	public void onDialogPositiveClick(DialogFragment dialog);

	public void onDialogNegativeClick(DialogFragment dialog);
}
