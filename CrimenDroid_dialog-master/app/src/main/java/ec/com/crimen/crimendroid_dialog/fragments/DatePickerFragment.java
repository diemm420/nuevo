package ec.com.crimen.crimendroid_dialog.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ec.com.crimen.crimendroid_dialog.R;

/**
 * Created by cesaralcivar on 10/7/16.
 */
public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE="date";
    private DatePicker datePicker;
    public static final String EXTRA_DATE="ec.com.crimen.crimendroid_dialog.fragments.date";

    public static DatePickerFragment newInstance(Date date){
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_DATE,date);
        DatePickerFragment pickerFragment=new DatePickerFragment();
        pickerFragment.setArguments(bundle);
        return pickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Date fecha=(Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(fecha);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        View view= LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date,null);

        datePicker=(DatePicker)view.findViewById(R.id.dialog_date_picker);
        datePicker.init(year,month,day,null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int year=datePicker.getYear();
                        int month=datePicker.getMonth();
                        int day=datePicker.getDayOfMonth();
                        Date date=new GregorianCalendar(year,month,day).getTime();
                        sendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode,Date date){

        if(getTargetFragment()==null)
            return;

        Intent i=new Intent();
        i.putExtra(EXTRA_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
