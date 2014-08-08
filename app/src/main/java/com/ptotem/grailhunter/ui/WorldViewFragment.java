package com.ptotem.grailhunter.ui;



import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.ptotem.grailhunter.R;
import com.ptotem.grailhunter.core.GrailEngine;
import com.ptotem.grailhunter.core.GrailLoader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class WorldViewFragment extends Fragment
{
    private GrailEngine grailEngine;
    private ButtonAdapter buttonAdapter;
    private TextView lifeValue;
    private TextView goldValue;
    public WorldViewFragment() {
        // Required empty public constructor
    }

    public void setGrailEngine(GrailEngine grailEngine)
    {
        this.grailEngine=grailEngine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_world_view, container, false);
        lifeValue=(TextView)view.findViewById(R.id.lifeValue);
        goldValue=(TextView)view.findViewById(R.id.goldValue);
        GridView gridView=(GridView)view.findViewById(R.id.worldGrid);
        buttonAdapter=new ButtonAdapter();
        gridView.setAdapter(buttonAdapter);
        return view;
    }
    public void updateLife(int life)
    {
        lifeValue.setText(""+life);
    }
    public void updateGold(int gold)
    {
        goldValue.setText(""+gold);
    }
/*    public void onStart()
    {
        if(buttonAdapter!=null)
            buttonAdapter.setInitialMapNumber(4);
    }*/


    class ButtonAdapter extends BaseAdapter implements View.OnClickListener
    {
        private Button[] buttons=new Button[9];
        private int currentButtonIndex;
        private String greyedOutColor="#010101";
        private String originalColor="#333333";
        public int getCount()
        {
            return buttons.length;
        }
        public Object getItem(int position)
        {
            return buttons[position];
        }
        public long getItemId(int position)
        {
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Button button;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                button = new Button(parent.getContext());
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setOnClickListener(this);
                button.setTag(new Integer(position));
                button.setBackgroundColor(Color.parseColor(originalColor));
                buttons[position]=button;
                if(position==GrailLoader.initialMapLocationIndex)
                    setInitialMapNumber(GrailLoader.initialMapLocationIndex);
            } else {
                button = (Button) convertView;
            }
            return button;
        }
        public void onClick(View view)
        {
            if(!grailEngine.canUseWorldMap())
                return;
            Button button=(Button)view;
            Integer index=(Integer)button.getTag();
            if(index.intValue()==currentButtonIndex) {
                //buttons[0].setBackgroundColor(Color.parseColor("#484848"));
                return;
            }
            buttons[currentButtonIndex].setBackgroundColor(Color.parseColor(originalColor));
            button.setBackgroundColor(Color.parseColor(greyedOutColor));
            grailEngine.travelToWorld(index.intValue());
            currentButtonIndex=index.intValue();
        }
        public void setInitialMapNumber(int gridNumber)
        {
            buttons[gridNumber].setBackgroundColor(Color.parseColor(greyedOutColor));
            currentButtonIndex=gridNumber;
        }
    }
}
