package com.example.deepika.click_fragment;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Result_Fragment extends Fragment implements View.OnClickListener {

    TextView enter,congo, result;
    EditText playerName;
    Button newGame,add;
    String name, RESULT="RESULT";
    ButtonAction buttonAction;

    public Result_Fragment() {
        // Required empty public constructor
    }

    public static Result_Fragment newInstance(long t){
        Result_Fragment result_fragment = new Result_Fragment();
        Bundle bundle = new Bundle();
        bundle.putLong("time",t);
        result_fragment.setArguments(bundle);
        return result_fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_result, container, false);
        enter = (TextView)view.findViewById(R.id.title);
        playerName= (EditText)view.findViewById(R.id.playerName);
        congo= (TextView)view.findViewById(R.id.congo);
        result= (TextView)view.findViewById(R.id.result);
        newGame = (Button)view.findViewById(R.id.newGame);
        newGame.setOnClickListener(this);
        name= playerName.getText().toString();


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        long t = getArguments().getLong("time");
        result.setText(""+t);
    }

    // @Override
    public void timeTaken(long t) {
        result.setText("You finished in "+ t + " seconds");
    }

    @Override
    public void onClick(View v) {
        Button b= (Button)v;
        switch(b.getId()){
            case R.id.newGame: buttonAction.newGame();
                Log.d(RESULT,"newGame clicked");
                                break;
            case R.id.add: buttonAction.add();
                            break;
        }
    }

    public interface ButtonAction{
        public void add();
        public void newGame();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            buttonAction = (ButtonAction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCountListener");
        }

    }
}
