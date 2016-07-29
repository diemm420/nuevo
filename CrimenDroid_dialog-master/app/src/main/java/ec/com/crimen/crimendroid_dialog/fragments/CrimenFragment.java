package ec.com.crimen.crimendroid_dialog.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

import ec.com.crimen.crimendroid_dialog.R;
import ec.com.crimen.crimendroid_dialog.dominios.Crimen;
import ec.com.crimen.crimendroid_dialog.dominios.CrimenSingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimenFragment extends Fragment {

    private EditText edTitulo;
    private Button btnFecha;
    private CheckBox chResuelto;

    private Crimen crimen;

    private static final String ARG_CRIMEN_ID="crimenid";
    private static final String DIALOG_DATE="DialogDate";
    private static final int REQUEST_DATE=0;

    public static CrimenFragment newInstance(int id){
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_CRIMEN_ID,id);
        CrimenFragment crimenFragment=new CrimenFragment();
        crimenFragment.setArguments(bundle);
        return crimenFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id= getArguments().getInt(ARG_CRIMEN_ID);
        crimen = CrimenSingleton.get(getActivity()).getCrimen(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_crimen, container, false);

        edTitulo=(EditText)view.findViewById(R.id.fragment_crimen_edTitulo);
        btnFecha=(Button)view.findViewById(R.id.fragment_crimen_btnFecha);
        chResuelto=(CheckBox)view.findViewById(R.id.fragment_crimen_chResuelto);

        edTitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crimen.setTitulo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        chResuelto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crimen.setResuelto(isChecked);
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getFragmentManager();
                DatePickerFragment dialog=DatePickerFragment.newInstance(crimen.getFecha());
                dialog.setTargetFragment(CrimenFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });

        edTitulo.setText(crimen.getTitulo());
        chResuelto.setChecked(crimen.isResuelto());
        btnFecha.setText(crimen.getFecha().toString());

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode!= Activity.RESULT_OK)
            return;

        if(requestCode==REQUEST_DATE){
            Date date=(Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crimen.setFecha(date);
            btnFecha.setText(date.toString());
        }
    }
}
