package com.ptotem.grailhunter.ui;



import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ptotem.grailhunter.R;
import com.ptotem.grailhunter.core.Campaign;
import com.ptotem.grailhunter.core.City;
import com.ptotem.grailhunter.core.GrailEngine;
import com.ptotem.grailhunter.core.GrailLoader;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CityViewFragment extends Fragment implements View.OnClickListener
{
    private City currentCity;
    private TextView cityName;
    private ImageView cityImage;
    private RelativeLayout cityFrame;
    private GridView cityCampaigns;
    private TextView campaignInfo;
    private GrailEngine grailEngine;

    public CityViewFragment()
    {
        // Required empty public constructor
    }

    public void setCurrentCity(City currentCity)
    {
        this.currentCity=currentCity;
    }

    public void setGrailEngine(GrailEngine grailEngine)
    {
        this.grailEngine=grailEngine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_city_view, container, false);
        cityName=(TextView)view.findViewById(R.id.cityName);
        cityImage=(ImageView)view.findViewById(R.id.cityImage);
        cityFrame=(RelativeLayout)view.findViewById(R.id.cityFrame);
        ((Button)view.findViewById(R.id.leaveButton)).setOnClickListener(this);
        cityCampaigns=(GridView)view.findViewById(R.id.cityCampaigns);
        campaignInfo=(TextView)inflater.inflate(R.layout.layout_campaign_info,cityFrame,false);
        checkCityViewFragmentUi();
        if(currentCity!=null)
            populateViewWithData(currentCity);
        return view;
    }

    public void populateViewWithData(City city)
    {
        cityName.setText(city.getName());
        cityImage.setImageResource(city.getIcon());
        cityCampaigns.setAdapter(new CampaignAdapter(city));
    }

    public void travelTo(City city)
    {
        if(city!=this.currentCity)
        {
            populateViewWithData(city);
            this.currentCity = city;
        }
    }

    public void onClick(View v)
    {
        grailEngine.leaveCity(currentCity);
    }

    public void setCampaignInfoText(String text)
    {
        this.campaignInfo.setText(text);
    }

    public void replaceWithCampaignInfo()
    {
        cityFrame.removeAllViews();
        cityFrame.addView(campaignInfo);
        Log.d("grails","replaced.");
    }

    public void checkCityViewFragmentUi()
    {
        if(cityFrame.getChildAt(0)==cityCampaigns)
            return;
        else
        {
            cityFrame.removeAllViews();
            cityFrame.addView(cityCampaigns);
        }
    }

    class CampaignAdapter extends BaseAdapter implements View.OnClickListener
    {
        private CampaignView[] campaignViews;
        private City city;
        private Campaign[] campaigns;
        public CampaignAdapter(City city)
        {
            this.city=city;
            city.getDtype();
            campaigns=grailEngine.getRandomCampaigns(city.getDtype());
            campaignViews=new CampaignView[campaigns.length];
        }
        public int getCount()
        {
            return campaigns.length;
        }
        public Object getItem(int position)
        {
            return (Object)campaignViews[position];
        }
        public long getItemId(int position)
        {
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent)
        {
            CampaignView campaignView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                campaignView = new CampaignView(parent.getContext(),campaigns[position]);
                campaignView.setOnClickListener(this);
                campaignView.setTag(campaigns[position]);
                campaignViews[position]=campaignView;
            } else {
                campaignView = (CampaignView) convertView;
            }
            return campaignView;
        }
        public void onClick(View view)
        {
            Campaign campaign=(Campaign)view.getTag();
            grailEngine.playCampaign(campaign,city);
        }
    }
}
