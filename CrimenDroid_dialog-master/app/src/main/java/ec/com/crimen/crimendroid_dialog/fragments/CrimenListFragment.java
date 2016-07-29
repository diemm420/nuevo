package ec.com.crimen.crimendroid_dialog.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.util.List;
import java.util.UUID;

import ec.com.crimen.crimendroid_dialog.CrimenActivity;
import ec.com.crimen.crimendroid_dialog.R;
import ec.com.crimen.crimendroid_dialog.dominios.Crimen;
import ec.com.crimen.crimendroid_dialog.dominios.CrimenSingleton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimenListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CrimenAdapter crimenAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_crimen_list, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_crimen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        String url="http://192.168.0.124:8080/crimenes";

        RequestQueue queue= Volley.newRequestQueue(getActivity());

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("CRIMEN",response);
                        Gson gson=new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
                        List<Crimen> result= gson.fromJson(response,
                                new TypeToken<List<Crimen>>(){}.getType());

                        Log.d("CRIMEN",result.size()+"");
                        

                        crimenAdapter=new CrimenAdapter(result);
                        recyclerView.setAdapter(crimenAdapter);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CRIMEN",error.getMessage());
            }
        });

        queue.add(stringRequest);

        return view;
    }

    private void updateUI(){
        CrimenSingleton crimenSingleton=CrimenSingleton.get(getActivity());
        if(crimenAdapter==null){
            crimenAdapter=new CrimenAdapter(crimenSingleton.getCrimens());
            recyclerView.setAdapter(crimenAdapter);
        }else{
            crimenAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class CrimenAdapter extends RecyclerView.Adapter<CrimenHolder>{

        private List<Crimen> crimens;

        public CrimenAdapter(List<Crimen> crimens){
            this.crimens=crimens;
        }

        @Override
        public CrimenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.list_item_crimen,parent,false);
            return new CrimenHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimenHolder holder, int position) {
            Crimen crimen=crimens.get(position);
            holder.bindCrimen(crimen);
        }

        @Override
        public int getItemCount() {
            return this.crimens.size();
        }
    }

    private class CrimenHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CheckBox chResuelto;
        private TextView txtTitulo;
        private TextView txtFecha;
        private Crimen crimen;

        public CrimenHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            chResuelto=(CheckBox)itemView.findViewById(R.id.list_item_crimen_chResuelto);
            txtTitulo=(TextView)itemView.findViewById(R.id.list_item_crimen_txtTitulo);
            txtFecha=(TextView)itemView.findViewById(R.id.list_item_crimen_txtFecha);
        }

        public void bindCrimen(Crimen crimen){
            this.crimen=crimen;
            txtTitulo.setText(crimen.getTitulo());
            chResuelto.setChecked(crimen.isResuelto());
            txtFecha.setText(crimen.getFecha().toString());
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getContext(),crimen.getTitulo(),Toast.LENGTH_SHORT).show();
            Intent i= CrimenActivity.newInstance(getContext(),crimen.getId());
            startActivity(i);
        }
    }
}
