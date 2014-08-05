package com.ptotem.grailhunter.com.ptotem.grailhunter.core;

/**
 * Created by vikram on 05/08/14.
 */
public class GrailLoader {
    public static int gameLevel=2;
    public static int travelCost=100;

    String[] icons={"HillTown.png", "Castle.png", "Necropolis.png", "Citadel.png", "Stronghold.png"};
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
    String[][] campaigns={
            {"1", "Small Treasure Hunt", "img/treasureroad/campaigns/treasure.png", "Pay 100 Gold to get 0 - 300 Gold", "100", "100", "Gold", "0", "300", "Gold"},
            {"2", "Large Treasure Hunt", "img/treasureroad/campaigns/treasure.png", "Pay 300 Gold to get 0 - 1000 Gold", "300", "300", "Gold", "0", "1000", "Gold"},
            {"3", "Rest at an Inn", "img/treasureroad/campaigns/inn.png", "Pay 100 Gold to get 0 - 30 Life", "100", "100", "Gold", "0", "30", "Life"},
            {"4", "Visit a Doctor", "img/treasureroad/campaigns/doctor.png", "Pay 300 Gold to get 100 Life", "300", "300", "Gold", "100", "100", "Life"},
            {"5", "Gladiator Contest", "img/treasureroad/campaigns/gladiators.png", "You will lose 0 - 50 Life to get 0-500 Gold", "0", "50", "Life", "0", "500", "Gold"},
            {"6", "Meet the Sphinx", "img/treasureroad/campaigns/sphinx.png", "Answer a question to learn of the location of the Grail","0","0","Question","0","0","Answer"}
    };
    String[][] defaultCampaigns={
            {"1", "Grail Expedition", "img/treasureroad/campaigns/treasure.png", "Pay 500 Gold to mount the expedition", "500", "500", "Gold", "0", "0", "Gold"}
    };


    public void initMap(Map map)
    {
        MapLocation[] mapLocations=new MapLocation[9];
        for (int i=0;i<locations.length;i++)
        {
            int id=Integer.parseInt(locations[i][0]);
            int lx=Integer.parseInt(locations[i][1]);
            int ly=Integer.parseInt(locations[i][2]);
            int desCount=(int)Math.floor(Math.random()*(GrailLoader.gameLevel+2)+1);
            int mapNum=(int)Math.floor((Math.random()*9)+1);
            mapLocations[i]=new MapLocation(id,lx,ly,locations[i][3],desCount,mapNum);
        }
        createCities(mapLocations);
        map.setMapLocations(mapLocations);

    }
    private void createCities(MapLocation[] mapLocations)
    {
        for(MapLocation mapLocation:mapLocations)
        {
            City[] citiesOfMap=new City[mapLocation.getDestinationCount()];
            for(int i=0;i<mapLocation.getDestinationCount();i++)
            {
                String icon=icons[(int)Math.floor(Math.random()*icons.length)];
                String name=icons[(int)Math.floor(Math.random()*cities.length)];
                int posx=(int)Math.floor((Math.random()*20)+5);
                int posy=(int)Math.floor((Math.random()*20)+5);
                int dtype=(int)Math.floor((Math.random()*2)+1);
                citiesOfMap[i]=new City(mapLocation.getLx(),mapLocation.getLy(),icon,name,posx,posy,dtype);
            }
            mapLocation.setCities(citiesOfMap);
        }
    }
    //creates @param:nums number of campaigns, excluding the grail expedition.
    public Campaign[] createCampaigns(int num)
    {
        Campaign[] newCampaigns=new Campaign[num+1];
        for(int i=0;i<num;i++)
        {
            int randomNumber=(int)Math.floor(Math.random()*campaigns.length);
            int id = Integer.parseInt(campaigns[randomNumber][0]);
            String name = campaigns[randomNumber][1];
            String icon = campaigns[randomNumber][2];
            String description = campaigns[randomNumber][3];
            int costMin = Integer.parseInt(campaigns[randomNumber][4]);
            int costMax = Integer.parseInt(campaigns[randomNumber][5]);
            String costCurrency = campaigns[randomNumber][6];
            int payoffMin = Integer.parseInt(campaigns[randomNumber][7]);
            int payoffMax = Integer.parseInt(campaigns[randomNumber][8]);
            String payoffCurrency = campaigns[randomNumber][9];
            newCampaigns[i]=new Campaign(id,name,icon,description,costMin,costMax,costCurrency,payoffMin,payoffMax,payoffCurrency);
        }
        int id = Integer.parseInt(campaigns[0][0]);
        String name = campaigns[0][1];
        String icon = campaigns[0][2];
        String description = campaigns[0][3];
        int costMin = Integer.parseInt(campaigns[0][4]);
        int costMax = Integer.parseInt(campaigns[0][5]);
        String costCurrency = campaigns[0][6];
        int payoffMin = Integer.parseInt(campaigns[0][7]);
        int payoffMax = Integer.parseInt(campaigns[0][8]);
        String payoffCurrency = campaigns[0][9];
        newCampaigns[newCampaigns.length-1]=new Campaign(id,name,icon,description,costMin,costMax,costCurrency,payoffMin,payoffMax,payoffCurrency);
        return newCampaigns;
    }
}
