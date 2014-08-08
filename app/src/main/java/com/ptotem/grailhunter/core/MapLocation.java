package com.ptotem.grailhunter.core;

/**
 * Created by vikram on 05/08/14.
 */
public class MapLocation
{
    private int id,lx,ly,destinationCount,mapNum;
    private String name;
    private City[] cities;

    public MapLocation(int id,int lx,int ly,String name,int destinationCount,int mapNum){
        this.id=id;
        this.lx=lx;
        this.ly=ly;
        this.name=name;
        this.destinationCount=destinationCount;
        cities=new City[destinationCount];
        this.mapNum=mapNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public int getLy() {
        return ly;
    }

    public void setLy(int ly) {
        this.ly = ly;
    }

    public int getDestinationCount() {
        return destinationCount;
    }

    public void setDestinationCount(int destinationCount) {
        this.destinationCount = destinationCount;
    }

    public int getMapNum() {
        return mapNum;
    }

    public void setMapNum(int mapNum) {
        this.mapNum = mapNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities)
    {
        this.cities=cities;
    }

    public boolean hasNorth()
    {
        if(this.ly==1)
            return false;
        else return true;
    }

    public boolean hasSouth()
    {
        if(this.ly==3)
            return false;
        else return true;
    }

    public boolean hasWest()
    {
        if(this.lx==1)
            return false;
        else return true;
    }

    public boolean hasEast()
    {
        if(this.ly==3)
            return false;
        else return true;
    }

    public boolean hasNorthEast()
    {
        return hasNorth()&&hasEast();
    }

    public boolean hasNorthWest()
    {
        return hasNorth()&&hasWest();
    }

    public boolean hasSouthEast()
    {
        return hasSouth()&&hasEast();
    }

    public boolean hasSouthWest()
    {
        return hasSouth()&&hasWest();
    }
}
