package ec.com.crimen.crimendroid_dialog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

import ec.com.crimen.crimendroid_dialog.fragments.CrimenFragment;

public class CrimenActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIMEN_ID="ec.com.crimen.crimendroid_fragmentarguments.crimen_id";

    public static Intent newInstance(Context context, int id){
        Intent i=new Intent(context,CrimenActivity.class);
        i.putExtra(EXTRA_CRIMEN_ID,id);
        return i;
    }

    @Override
    public Fragment createFragment() {
        int id=getIntent().getIntExtra(EXTRA_CRIMEN_ID,0);
        return CrimenFragment.newInstance(id);
    }
}
