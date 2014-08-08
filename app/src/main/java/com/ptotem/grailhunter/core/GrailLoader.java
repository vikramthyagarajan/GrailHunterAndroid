package com.ptotem.grailhunter.core;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ptotem.grailhunter.R;

/**
 * Created by vikram on 05/08/14.
 */
public class GrailLoader {
    public static int gameLevel=2;
    public static final int travelCost=100;
    public static final int initialLife=100;
    public static final int initialGold=500;
    public static final int grailCampaignId=13;
    public static final int initialMapLocationIndex=4;
    private static final int xMultiplier=60,yMultiplier=42;
    int[] icons={R.drawable.icons_hilltown, R.drawable.icons_castle, R.drawable.icons_necropolis, R.drawable.icons_citadel, R.drawable.icons_stronghold};
    public static final String[] responses={"You can't afford that!","Uh-oh, that's wrong!","The wise man knows when to stop digging",
            "That was a waste of money!","You found the Grail!"};
    String[][] locations={
            {"1", "1", "1", "Erindor",},
            {"2", "2", "1", "Mithkoti",},
            {"3", "3", "1", "Gragoo", },
            {"4", "1", "2", "Ux", },
            {"5", "2", "2", "Florifort", },
            {"6", "3", "2", "Lorilia", },
            {"7", "1", "3", "Xyq", },
            {"8", "2", "3", "Joreberg",},
            {"9", "3", "3", "Poliwip",}
    };
    String[] cities={"Rayris", "Dowor", "Slale", "Kalist", "Ightdton", "Stycha", "Tregha", "Rying", "Aughgha", "Garghae",
            "Kelash","Lir", "Dendela", "Ildyrt", "Morrage", "Lyesdra", "Omsul", "Irrode", "Polburu", "Aleeph", "Burunt",
            "Aleuch", "Reough", "Rodid", "Issaz", "Crykim", "Nalyv", "Whean", "Schaem", "Kimwor", "Tinul", "Hiril", "Kallori",
            "Tasrane", "Hinkalo", "Cyer", "Echtai", "Rothziss", "Slikin", "Ightas", "Ashis", "Isoz", "Delkimi", "Honril",
            "Darjper", "Quidel", "Oldyrt", "Delhund", "Osach", "Athaq", "Anrili", "Oughyer", "Woren", "Shyight", "Schoqua",
            "Hitur", "Anglery", "Issey", "Radum", "Ialde", "Radlest", "Garbque", "Athpolo", "Cerkal", "Aleser", "Rhas",
            "Shyuch", "Inapina", "Queust", "Leden", "Quatori", "Duton", "Hinkaw", "Ildight", "Turdund", "Burxeng", "Rothid",
            "Enthyt", "Belessa", "Atbaugh", "Arding", "Rothom", "Theras", "Tasbura", "Mosesti", "Lytur", "Aroth", "Hatynt",
            "Rodhat", "Honlgha", "Zitan", "Emeta", "Bias", "Nalbyer", "Vesorm", "Perut", "Radworu", "Denat", "Danbhon",
            "Athsqua", "Garilt", "Kelen", "Skelton", "Imppol", "Dynok", "Omdang", "Issemo", "Whaim", "Roas", "Aleit", "Oughelm",
            "Kimrod"
    };
    int[] mapNum={R.drawable.maps_map1,R.drawable.maps_map2,R.drawable.maps_map3,R.drawable.maps_map4,R.drawable.maps_map5,
            R.drawable.maps_map6,R.drawable.maps_map7,R.drawable.maps_map8,R.drawable.maps_map9,R.drawable.maps_map10
    };
    String[][] campaigns={
            {"1", "Small Treasure Hunt", "img/treasureroad/campaigns/treasure.png", "Pay 100 Gold to get 0 - 300 Gold", "100", "100", "Gold", "0", "300", "Gold"},
            {"2", "Large Treasure Hunt", "img/treasureroad/campaigns/treasure.png", "Pay 300 Gold to get 0 - 1000 Gold", "300", "300", "Gold", "0", "1000", "Gold"},
            {"3", "Rest at an Inn", "img/treasureroad/campaigns/inn.png", "Pay 100 Gold to get 0 - 30 Life", "100", "100", "Gold", "0", "30", "Life"},
            {"4", "Visit a Doctor", "img/treasureroad/campaigns/doctor.png", "Pay 300 Gold to get 100 Life", "300", "300", "Gold", "100", "100", "Life"},
            {"5", "Gladiator Contest", "img/treasureroad/campaigns/gladiators.png", "You will lose 0 - 50 Life to get 0-500 Gold", "0", "50", "Life", "0", "500", "Gold"},
            {"6", "Meet the Sphinx", "img/treasureroad/campaigns/sphinx.png", "Answer a question to learn of the location of the Grail","0","0","Question","0","0","Answer"}
    };
    String[][] defaultCampaigns={
            {"13", "Grail Expedition", "img/treasureroad/campaigns/treasure.png", "Pay 500 Gold to mount the expedition", "500", "500", "Gold", "0", "0", "Gold"}
    };
    //The last element in the array is the answer. 1 for option a, 2 for b... etc.
    String[][] questions={
            {"1", "5", "In which city is the annual Running of the Bulls festival held?", "Pamplona", "Madrid", "Valencia", "Bunol","1" },
            {"2", "10", "The Guindy National Park is located within the urban limits of which state capital in India?", "Mumbai", "Hyderabad", "Chennai", "Cochin","3" },
            {"3", "20", "In which country can you see the Catatumbo Lightning, a spectacular natural phenomenon?", "India", "Russia", "Venezuela", "USA","3" }
    };

    public void initMap(Map map,Context context)
    {
        MapLocation[] mapLocations=new MapLocation[9];
        for (int i=0;i<locations.length;i++)
        {
            int id=Integer.parseInt(locations[i][0]);
            int lx=Integer.parseInt(locations[i][1]);
            int ly=Integer.parseInt(locations[i][2]);
            int desCount=(int)Math.floor(Math.random()*(GrailLoader.gameLevel+2)+1);
            int mapNumber=(int)Math.floor((Math.random()*9)+1);
            mapLocations[i]=new MapLocation(id,lx,ly,locations[i][3],desCount,mapNum[mapNumber]);
        }
        createCities(mapLocations,context);
        map.setMapLocations(mapLocations);
    }
    public Grail createGrail(Map map)
    {
        MapLocation[] all=map.getMapLocations();
        int index1=(int)Math.floor(Math.random()*all.length);
        City[] grailLoc=all[index1].getCities();
        int index2=(int)Math.floor(Math.random()*grailLoc.length);
        Grail grail=new Grail(all[index1],grailLoc[index2]);
        return grail;
    }
    private void createCities(MapLocation[] mapLocations,Context context)
    {
        for(MapLocation mapLocation:mapLocations)
        {
            City[] citiesOfMap=new City[mapLocation.getDestinationCount()];
            for(int i=0;i<mapLocation.getDestinationCount();i++)
            {
                int icon=icons[(int)Math.floor(Math.random()*icons.length)];
                String name=cities[(int)Math.floor(Math.random()*cities.length)];
                int posx=(int)Math.floor((Math.random()*10));
                int posy=(int)Math.floor((Math.random()*10));
                int dtype=(int)Math.floor((Math.random()*2)+1);
                Button button=new Button(context);
                RelativeLayout.LayoutParams lay=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                lay.addRule(RelativeLayout.BELOW,R.id.mapLocationName);
                lay.setMargins(posx*xMultiplier,posy*yMultiplier,0,0);
                button.setLayoutParams(lay);
                button.setBackgroundResource(icon);
                citiesOfMap[i]=new City(mapLocation.getLx(),mapLocation.getLy(),icon,name,posx,posy,dtype,button);
            }
            mapLocation.setCities(citiesOfMap);
        }
    }

    public Campaign[] createCampaigns()
    {
        Campaign[] camps=new Campaign[campaigns.length+1];
        for(int i=0;i<campaigns.length;i++)
        {
            String[] campaign=campaigns[i];
            int id = Integer.parseInt(campaign[0]);
            String name = campaign[1];
            String icon = campaign[2];
            String description = campaign[3];
            int costMin = Integer.parseInt(campaign[4]);
            int costMax = Integer.parseInt(campaign[5]);
            String costCurrency = campaign[6];
            int payoffMin = Integer.parseInt(campaign[7]);
            int payoffMax = Integer.parseInt(campaign[8]);
            String payoffCurrency = campaign[9];
            camps[i]=new Campaign(id,name,icon,description,costMin,costMax,costCurrency,payoffMin,payoffMax,payoffCurrency);
        }
        String[] campaign=defaultCampaigns[0];
        int id = Integer.parseInt(campaign[0]);
        String name = campaign[1];
        String icon = campaign[2];
        String description = campaign[3];
        int costMin = Integer.parseInt(campaign[4]);
        int costMax = Integer.parseInt(campaign[5]);
        String costCurrency = campaign[6];
        int payoffMin = Integer.parseInt(campaign[7]);
        int payoffMax = Integer.parseInt(campaign[8]);
        String payoffCurrency = campaign[9];
        camps[campaigns.length]=new Campaign(id,name,icon,description,costMin,costMax,costCurrency,payoffMin,payoffMax,payoffCurrency);
        return camps;
    }
    public Question[] createQuestions()
    {
        Question[] quests=new Question[questions.length];
        for(int i=0;i<questions.length;i++)
        {
            int id = Integer.parseInt(questions[i][0]);
            int difficulty=Integer.parseInt(questions[i][1]);
            String name = questions[i][2];
            String opta=questions[i][3];
            String optb=questions[i][4];
            String optc=questions[i][5];
            String optd=questions[i][6];
            int answer=Integer.parseInt(questions[i][7]);
            quests[i]=new Question(id,difficulty,name,opta,optb,optc,optd,answer);
        }
        return quests;
    }

}
