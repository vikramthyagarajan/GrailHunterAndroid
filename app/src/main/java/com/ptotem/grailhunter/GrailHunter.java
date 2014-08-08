package com.ptotem.grailhunter;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ptotem.grailhunter.core.GrailEngine;
import com.ptotem.grailhunter.ui.MapViewFragment;
import com.ptotem.grailhunter.ui.WorldViewFragment;

public class GrailHunter extends Activity
{
    private boolean doubleBackToExitPressedOnce=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grail_hunter);
        GrailEngine grailEngine=new GrailEngine();
        grailEngine.loadGame(this);
        grailEngine.setGrailHunterActivity(this);
        grailEngine.setMapViewFragment((MapViewFragment)getFragmentManager().findFragmentById(R.id.mapViewFragment));
        WorldViewFragment worldViewFragment=(WorldViewFragment)getFragmentManager().findFragmentById(R.id.worldViewFragment);
        MapViewFragment mapViewFragment=(MapViewFragment)getFragmentManager().findFragmentById((R.id.mapViewFragment));
        grailEngine.setWorldViewFragment(worldViewFragment);
        worldViewFragment.setGrailEngine(grailEngine);
        mapViewFragment.setGrailEngine(grailEngine);
        grailEngine.initializeGameUi();
    }


    public void replaceFragmentsInActivity(Fragment oldFragment,Fragment newFragment)
    {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.remove(oldFragment);
        fragmentTransaction.add(R.id.fragmentFrame,newFragment);
        //fragmentTransaction.replace(R.id.fragmentFrame,newFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void restoreOldFragment()
    {
        getFragmentManager().popBackStack();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grail_hunter, menu);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce)
        {
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to end game", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
