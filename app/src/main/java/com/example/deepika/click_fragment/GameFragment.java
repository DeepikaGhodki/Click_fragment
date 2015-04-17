package com.example.deepika.click_fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    int count = 0;
    public static final String GAME = "GAME";
    DisplayMetrics display = new DisplayMetrics();
    long t1 = 0, t2 = 0, time = t2 - t1;
    Button start;
    TextView number,title;
    FrameLayout layout;
    OnCountListener countObj;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(GAME," in onCreateView");
        View view =inflater.inflate(R.layout.fragment_game, container, false);

        start = (Button) view.findViewById(R.id.start);
        number = (TextView) view.findViewById(R.id.noOfClicks);
        layout = (FrameLayout) view.findViewById(R.id.gameFrame);
        title= (TextView)view.findViewById(R.id.title);
        //Toast.makeText(getActivity(),"Click the button 20 times as fast as you can", Toast.LENGTH_SHORT);
        //Log.d(GAME,"toast");
        start.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String no = String.valueOf(count);
                Log.d(GAME, "On Click");
                if (count == 0) {
                    start.setText("Click Me");
                    title.setText("");
                    t1 = System.currentTimeMillis();
                } else {
                    number.setText(no);
                    if (count == 5) {
                        start.setEnabled(false);
                        t2 = System.currentTimeMillis();
                        //Toast.makeText(getActivity(),"Time taken is "+ String.valueOf((t2-t1))+ " milliseconds",Toast.LENGTH_SHORT).show();
                        Log.d(GAME, "time "+String.valueOf(t2 - t1));
                        countObj.onTwenty(t2-t1);


                    }
                }
                count++;
                Log.i(GAME, String.valueOf(t1 / 1000));
                Log.i(GAME, String.valueOf(t2 / 1000));
                Log.i(GAME, String.valueOf(t2 - t1));
                setPos(start);

            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void setPos(Button b) {

        float x, y;

        x = (float) ((float) Math.random() * (layout.getWidth() - b.getWidth()));
        y = (float) ((float) Math.random() * (layout.getHeight() - b.getHeight()));


        x = Math.abs(x);
        y = Math.abs(y);
        Log.i(GAME, String.valueOf(x));
        Log.i(GAME, String.valueOf(y));
        // Log.i("button w:", String.valueOf(b.getWidth())); //200px
        //Log.i("button h:", String.valueOf(b.getHeight()));//100px
        // Log.i("screen w:", String.valueOf(display.widthPixels)); 720pixels
        // Log.i("screen h:", String.valueOf(display.heightPixels)); //1184pixels
        //Log.i("layout height",String.valueOf(layout.getHeight())); 1022px

        start.setX(x);
        start.setY(y);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            countObj = (OnCountListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCountListener");
        }

    }

    public interface OnCountListener {
            public void onTwenty(long t);
        }

}
