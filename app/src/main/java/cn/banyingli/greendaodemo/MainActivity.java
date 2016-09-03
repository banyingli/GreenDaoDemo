package cn.banyingli.greendaodemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements BaseFragment.BackHandledInterface {

    private BaseFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            BaseFragment editFragment = new EditConcertFragment();
            loadFragment(editFragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadFragment(BaseFragment fragment) {
        BaseFragment second = fragment;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, second, "other");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack("tag");
        ft.commit();
    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.mFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        if (mFragment == null || !mFragment.onBackPressed()) {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                super.onBackPressed();
            } else {
                getFragmentManager().popBackStack();
            }
        }
    }

}
