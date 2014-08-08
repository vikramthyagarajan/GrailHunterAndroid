package com.ptotem.grailhunter.ui;



import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ptotem.grailhunter.R;
import com.ptotem.grailhunter.core.City;
import com.ptotem.grailhunter.core.GrailEngine;
import com.ptotem.grailhunter.core.MapLocation;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MapViewFragment extends Fragment implements View.OnClickListener
{

    private MapLocation currentMapLocation;
    private TextView mapLocationName;
    private RelativeLayout mapBackground;
    private Context context;
    private Button[] cityButtons;
    private GrailEngine grailEngine;

    public MapViewFragment() {
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
        View view=inflater.inflate(R.layout.fragment_map_view, container, false);
        context=view.getContext();
        mapLocationName=(TextView)view.findViewById(R.id.mapLocationName);
        mapBackground=(RelativeLayout)view.findViewById(R.id.mapBackground);
        return view;
    }

    private void populateMapWithData(MapLocation mapLocation)
    {
        //mapBackground.setBackgroundResource(mapLocation.getMapNum());
        mapLocationName.setText(mapLocation.getName());
        City[] cities=mapLocation.getCities();
        cityButtons=new Button[cities.length];
        for(int i=0;i<cities.length;i++)
        {
            City city=cities[i];
            Button button=city.getButton();
            cityButtons[i]=button;
            button.setOnClickListener(this);
            button.setTag(city);
            //lay.addRule(RelativeLayout.BELOW,R.id.mapLocationName);
            mapBackground.addView(button);
        }
    }

    public void resetOldViews(View[] views)
    {
        if(views==null)
            return;
        for(View view:views)
        {
            mapBackground.removeView(view);
        }
    }
    public void travelTo(MapLocation mapLocation)
    {
        if(mapLocation!=this.currentMapLocation)
        {
            resetOldViews(cityButtons);
            populateMapWithData(mapLocation);
            currentMapLocation=mapLocation;
        }
    }
    public int numberOfCitiesRemaining()
    {
        return mapBackground.getChildCount()-1;
    }
    public void removeCityButton(Button cityButton)
    {
        mapBackground.removeView(cityButton);
    }
    public void onClick(View view)
    {
        grailEngine.travelToCity((City)view.getTag());
    }

}
