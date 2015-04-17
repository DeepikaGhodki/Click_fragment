package com.example.deepika.click_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity implements GameFragment.OnCountListener, Result_Fragment.ButtonAction {

    public static String MAIN= "MAIN";
    long t;
    SendDataToFragment dataToFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //apply layout in the usual way
        if(savedInstanceState==null) {
           // Toast.makeText(this,"Click the button 20 times as fast as you can", Toast.LENGTH_SHORT);
            //Log.d(MAIN, "toast");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.game, new GameFragment())
                    .commit();

        }

        if(findViewById(R.id.score)!=null){
            Fragment f= new ScoreFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.score, f)
                    .commit();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //This gets called by the GameFragment when the user clicks the button 20th time
    @Override
    public void onTwenty(long t) {
            this.t=t;

        Result_Fragment f1 = new Result_Fragment();
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.game, f1).addToBackStack(null).commit(); //  FrameLayout container
//            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
           // ft.addToBackStack(null);
           // ft.commit();
        //dataToFragment.timeTaken(t);
        Toast.makeText(this,"Time is "+ t, Toast.LENGTH_SHORT).show();


            }

    @Override
    public void add() {
        ;
    }

    @Override
    public void newGame() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.game, new GameFragment())
                .commit();
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //View rootView = inflater.inflate(R.layout.fragment_game, container, false);
            View rootView = inflater.inflate(R.layout.fragment_game, container, false);
            return rootView;
        }
    }

    public interface SendDataToFragment{
        public void timeTaken(long t);
    }
}

