package ec.com.crimen.crimendroid_dialog;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ec.com.crimen.crimendroid_dialog.fragments.CrimenListFragment;

public class CrimenListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CrimenListFragment();
    }
}
