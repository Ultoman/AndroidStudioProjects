package edu.niu.cs.z1764108.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;


public class ListFragment extends Fragment {
    private OnItemSelectedListener listener;
    private String information;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button plumBtn = (Button)view.findViewById(R.id.plumButton);
        Button blueBtn = (Button)view.findViewById(R.id.blueButton);
        Button goldBtn = (Button)view.findViewById(R.id.goldButton);

        plumBtn.setOnClickListener(shadeChange);
        blueBtn.setOnClickListener(shadeChange);
        goldBtn.setOnClickListener(shadeChange);
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    private View.OnClickListener shadeChange = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            information = (String)v.getContentDescription();
            listener.onShadeItemSelected(information);

        }
    };

    public interface OnItemSelectedListener
    {
        public void onShadeItemSelected(String colorInfo);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener)activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

}
