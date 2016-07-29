package ec.com.crimen.crimendroid_dialog.dominios;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by cesaralcivar on 25/6/16.
 */
public class CrimenSingleton {

    private static CrimenSingleton crimenSingleton;
    private List<Crimen> crimens;

    private CrimenSingleton(Context context){
        crimens=new ArrayList<>();
        for(int i=0;i<100;i++){
            Crimen crimen=new Crimen();
            crimen.setTitulo("Crimen #"+i);
            crimen.setResuelto(i%2==0);
            crimens.add(crimen);
        }
    }

    public static CrimenSingleton get(Context context){
        if(crimenSingleton==null)
            crimenSingleton=new CrimenSingleton(context);
        return crimenSingleton;
    }

    public List<Crimen> getCrimens(){
        return crimens;
    }

    public Crimen getCrimen(int id){
        for(Crimen crimen:crimens){
            if(crimen.getId()==id)
                return crimen;
        }
        return null;
    }
}
