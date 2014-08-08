package com.ptotem.grailhunter.core;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ptotem.grailhunter.GameOver;
import com.ptotem.grailhunter.GrailHunter;
import com.ptotem.grailhunter.ui.CityViewFragment;
import com.ptotem.grailhunter.ui.QuestionViewFragment;
import com.ptotem.grailhunter.ui.WorldViewFragment;
import com.ptotem.grailhunter.ui.MapViewFragment;

/**
 * Created by vikram on 05/08/14.
 */
public class GrailEngine
{
    private boolean canUseWorldMap=true;
    private Campaign[] campaigns;
    private Map map;
    private GrailLoader grailLoader;
    private int life,gold;
    private MapLocation currentMapLocation;
    private Question[] questions;
    private GrailHunter grailHunterActivity;
    private MapViewFragment mapViewFragment;
    private WorldViewFragment worldViewFragment;
    private CityViewFragment cityViewFragment;
    private Grail grail;
    private QuestionViewFragment questionViewFragment;


    public boolean canUseWorldMap()
    {
        return this.canUseWorldMap;
    }
    public void setMapViewFragment(MapViewFragment mapViewFragment)
    {
        this.mapViewFragment=mapViewFragment;
    }
    public void setWorldViewFragment(WorldViewFragment worldViewFragment)
    {
        this.worldViewFragment=worldViewFragment;
    }
    public void setGrailHunterActivity(GrailHunter activity)
    {
        this.grailHunterActivity=activity;
    }
    public void loadGame(Context context)
    {
        map=new Map();
        grailLoader=new GrailLoader();
        grailLoader.initMap(map,context);
        currentMapLocation=map.getMapLocations()[GrailLoader.initialMapLocationIndex];
        campaigns=grailLoader.createCampaigns();
        questions=grailLoader.createQuestions();
        grail=grailLoader.createGrail(map);
        life=GrailLoader.initialLife;
        gold=GrailLoader.initialGold;
        //grailLoader=null;             //comment out for better memory management. No more use for grailLoader.
    }
    public void initializeGameUi()
    {
        cityViewFragment=new CityViewFragment();
        cityViewFragment.setGrailEngine(this);
        questionViewFragment =new QuestionViewFragment();
        questionViewFragment.setGrailEngine(this);
        mapViewFragment.travelTo(currentMapLocation);
        worldViewFragment.updateLife(life);
        worldViewFragment.updateGold(gold);
    }
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void playCampaign(Campaign campaign,City city)
    {
        if(campaign.getId()==GrailLoader.grailCampaignId)
        {
            cityViewFragment.replaceWithCampaignInfo();
            if(gold<campaign.getCostMax())
            {
                cityViewFragment.setCampaignInfoText(GrailLoader.responses[0]);
            }
            playGrailQuest(campaign,city);
            return;
        }
        if(!campaign.getCostCurrency().equals("Question"))
        {
            cityViewFragment.replaceWithCampaignInfo();
            int cost=(int)Math.floor(Math.random() * (campaign.getCostMax()- campaign.getCostMin())) + campaign.getCostMin();
            int payoff=(int)Math.floor(Math.random() * (campaign.getPayoffMax()- campaign.getPayoffMin())) + campaign.getPayoffMin();
            if(campaign.getCostCurrency().equals("Gold"))
            {
               if(gold<campaign.getCostMax())
               {
                   cityViewFragment.setCampaignInfoText(GrailLoader.responses[0]);
                   return;
               }
               else
               {
                   gold-=cost;
               }
            }
            else
            {
                if(life<campaign.getCostMax())
                {
                    cityViewFragment.setCampaignInfoText(GrailLoader.responses[0]);
                    return;
                }
                else
                {
                    life-=cost;
                }
            }
            if(campaign.getPayoffCurrency().equals("Gold"))
                gold+=payoff;
            else
                life+=payoff;
            worldViewFragment.updateLife(life);
            worldViewFragment.updateGold(gold);
            cityViewFragment.setCampaignInfoText("You spent "+cost+" "+campaign.getCostCurrency()+" to gain "+payoff+" "+
                    campaign.getPayoffCurrency());
        }
        else
        {
            if(gold<=campaign.getCostMax())
            {
                cityViewFragment.replaceWithCampaignInfo();
                cityViewFragment.setCampaignInfoText(GrailLoader.responses[0]);
            }
            else
            {
                questionViewFragment.setQuestion(getRandomQuestion());
                questionViewFragment.setCity(city);
                grailHunterActivity.replaceFragmentsInActivity(cityViewFragment, questionViewFragment);
            }
        }
    }

    public void playGrailQuest(Campaign campaign,City city)
    {
        gold-=campaign.getCostMax();
        worldViewFragment.updateGold(gold);
        if(grail.getCity()==city)
        {
            cityViewFragment.setCampaignInfoText(GrailLoader.responses[4]);
            endGame();
        }
        else
        {
            cityViewFragment.setCampaignInfoText(GrailLoader.responses[3]);
        }
    }

    public Question getRandomQuestion()
    {
        int random=(int)Math.floor(Math.random()*questions.length);
        return questions[random];
    }

    public void travelToWorld(int world)
    {
        gold-=GrailLoader.travelCost;
        worldViewFragment.updateGold(gold);
        currentMapLocation=map.getMapLocations()[world];
        mapViewFragment.travelTo(currentMapLocation);
    }

    public void leaveCity(City city)
    {
        canUseWorldMap=true;
        mapViewFragment.removeCityButton(city.getButton());
        grailHunterActivity.restoreOldFragment();
        if(mapViewFragment.numberOfCitiesRemaining()==0&&gold<GrailLoader.travelCost)
            endGame();
    }

    public void leaveFromAnsweringMode(City city)
    {
        grailHunterActivity.restoreOldFragment();
        leaveCity(city);
    }

    public void travelToCity(City city)
    {
        canUseWorldMap=false;
        cityViewFragment.setCurrentCity(city);
        grailHunterActivity.replaceFragmentsInActivity(mapViewFragment,cityViewFragment);
        return;
    }
    //dtype is a variable that Cities have that relates to the number of campaigns the city should have.
    public Campaign[] getRandomCampaigns(int dtype)
    {
        Campaign[] camps=new Campaign[dtype+1];
        for(int i=0;i<dtype;i++)
        {
            //the last campaign is the grail expedition, which is default, and shouldn't be counted while randoming.
            int randomNumber=(int)Math.floor(Math.random()*(campaigns.length-1));
            camps[i]=campaigns[randomNumber];
        }
        camps[dtype]=campaigns[campaigns.length-1];
        return camps;
    }

    public void answerQuestion(boolean correct,int payoff)
    {
        questionViewFragment.replaceWithAnswerInfo();
        if(correct)
            questionViewFragment.setAnswerInfoText(grail.getGrailDirection(currentMapLocation,payoff));
        else
            questionViewFragment.setAnswerInfoText(GrailLoader.responses[3]);
        /*grailHunterActivity.restoreOldFragment();
        cityViewFragment.replaceWithCampaignInfo();
        if(correct)
            cityViewFragment.setCampaignInfoText(grail.getGrailDirection(currentMapLocation));
        else
            cityViewFragment.setCampaignInfoText(GrailLoader.responses[3]);*/
    }

    public void endGame()
    {
        Intent intent=new Intent(grailHunterActivity, GameOver.class);
        grailHunterActivity.startActivity(intent);
    }

}
