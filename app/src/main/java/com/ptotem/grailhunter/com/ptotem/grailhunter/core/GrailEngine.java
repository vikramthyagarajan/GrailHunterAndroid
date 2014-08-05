package com.ptotem.grailhunter.com.ptotem.grailhunter.core;

/**
 * Created by vikram on 05/08/14.
 */
public class GrailEngine
{
    public void loadGame()
    {
        map=new Map();
        grailLoader=new GrailLoader();
        grailLoader.initMap(map);
    }
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void playCampaign(Campaign campaign)
    {
        if(!campaign.getCostCurrency().equals("Question"))
        {
            int cost=(int)Math.floor(Math.random() * (campaign.getCostMax()- campaign.getCostMin())) + campaign.getCostMin();
            int payoff=(int)Math.floor(Math.random() * (campaign.getPayoffMax()- campaign.getPayoffMin())) + campaign.getPayoffMin();
            if(!campaign.getCostCurrency().equals("Gold"))
            {
               if(gold<campaign.getCostMax())
                   ;//TODO:- Implement some ui feedback saying user is a retard.
               else
               {
                   gold-=cost;
               }
            }
            else
            {
                if(life<campaign.getCostMax())
                    ;//TODO:- Implement some ui feedback saying user is a retard.
                else
                {
                    life-=cost;
                }
            }
            if(campaign.getPayoffCurrency().equals("Gold"))
                gold+=payoff;
            else
                life+=payoff;
        }
        else
        {
            getQuestion();
        }
    }
    private Map map;
    private GrailLoader grailLoader;
    private int life,gold;
    private int currentMapLocation;

}
