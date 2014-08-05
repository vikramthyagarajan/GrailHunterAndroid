package com.ptotem.grailhunter.com.ptotem.grailhunter.core;

/**
 * Created by vikram on 05/08/14.
 */
public class Map
{
    public MapLocation[] getMapLocations() {
        return mapLocations;
    }

    public void setMapLocations(MapLocation[] mapLocations) {
        this.mapLocations = mapLocations;
    }

    public MapLocation getMapLocationInDirection(MapLocation old,int direction)
    {
        switch (direction)
        {
            case NORTH:
                if(old.hasNorth())
                    return mapLocations[calculateIdOfLocation(old.getLx(),old.getLy()-1)];
                else return null;
            case SOUTH:
                if(old.hasSouth())
                    return mapLocations[calculateIdOfLocation(old.getLx(),old.getLy()+1)];
                else return null;
            case EAST:
                if(old.hasEast())
                    return mapLocations[calculateIdOfLocation(old.getLx()+1,old.getLy())];
                else return null;
            case WEST:
                if(old.hasWest())
                    return mapLocations[calculateIdOfLocation(old.getLx()-1,old.getLy())];
                else return null;
            case NORTHEAST:
                if(old.hasNorthEast())
                    return mapLocations[calculateIdOfLocation(old.getLx()+1,old.getLy()-1)];
                else return null;
            case NORTHWEST:
                if(old.hasNorthWest())
                    return mapLocations[calculateIdOfLocation(old.getLx()-1,old.getLy()-1)];
                else return null;
            case SOUTHEAST:
                if(old.hasSouthEast())
                    return mapLocations[calculateIdOfLocation(old.getLx()+1,old.getLy()+1)];
                else return null;
            case SOUTHWEST:
                if(old.hasSouthWest())
                    return mapLocations[calculateIdOfLocation(old.getLx()-1,old.getLy()+1)];
                else return null;
        }
        return null;
    }

    private int calculateIdOfLocation(int lx,int ly)
    {
        return ((ly-1)*3)+(lx-1)+1;
    }

    private MapLocation[] mapLocations=new MapLocation[9];
    public static final int NORTH=0;
    public static final int EAST=1;
    public static final int SOUTH=2;
    public static final int WEST=3;
    public static final int NORTHEAST=4;
    public static final int SOUTHEAST=5;
    public static final int SOUTHWEST=6;
    public static final int NORTHWEST=7;
}
