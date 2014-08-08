package com.ptotem.grailhunter.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ptotem.grailhunter.R;
import com.ptotem.grailhunter.core.Campaign;

/**
 * Created by vikram on 07/08/14.
 */
public class CampaignView extends RelativeLayout
{
    public CampaignView(Context c,AttributeSet attributeSet)
    {
        super(c,attributeSet);
    }
    public CampaignView(Context context,Campaign campaign)
    {
        super(context);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.view_campaign, this, true);
        ((TextView)findViewById(R.id.campaignName)).setText(campaign.getName());
        ((TextView)findViewById(R.id.campaignDescription)).setText(campaign.getDescription());
    }
}
