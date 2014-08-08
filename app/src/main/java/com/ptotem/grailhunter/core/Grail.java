package com.ptotem.grailhunter.core;

import android.util.Log;

/**
 * Created by vikram on 07/08/14.
 */
public class Grail
{
    private MapLocation mapLocation;
    private City city;
    public Grail(MapLocation mapLocation,City city)
    {
        this.mapLocation=mapLocation;
        this.city=city;
    }
    public String getGrailDirection(MapLocation mL,int payoff)
    {
        int toss=(int)Math.floor(Math.random()*2);
        int cpx=mL.getLx();
        int cpy= mL.getLy();
        int gpx=mapLocation.getLx();
        int gpy=mapLocation.getLy();
        Log.d("grails","curr "+cpx+":"+cpy+"     gr "+gpx+":"+gpy+"   "+city.getName());
        if(toss>0)
        {
            if (cpx == gpx) {
                if (cpy == gpy) {
                    return "The wise man knows when to stop looking and start digging";
                } else {
                    if (cpy > gpy) {
                        return "Head North to find the Grail";
                    } else {
                        return "Head South to find the Grail";
                    }
                }
            } else {
                if (cpx > gpx) {
                    return "Head West to find the Grail";
                } else {
                    return "Head East to find the Grail";
                }
            }
        }
        else
        {
            if (cpy == gpy) 
            {
                if (cpx == gpx) 
                {
                    return "The wise man knows when to stop looking and start digging";
                } 
                else 
                {
                    if (cpx > gpx) 
                    {
                        return "Head West to find the Grail";
                    } 
                    else 
                    {
                        return "Head East to find the Grail";
                    }
                }
            } 
            else 
            {
                if (cpy > gpy) 
                {
                    return "Head North to find the Grail";
                } 
                else 
                {
                    return "Head South to find the Grail";
                }
            }
        }
    }

    public MapLocation getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(MapLocation mapLocation) {
        this.mapLocation = mapLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
