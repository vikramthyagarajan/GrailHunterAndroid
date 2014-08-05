package com.ptotem.grailhunter.com.ptotem.grailhunter.core;

/**
 * Created by vikram on 05/08/14.
 */
public class City
{
    private int lx,ly,posx,posy,dtype;
    private String icon,name;
    public City(int lx,int ly,String icon,String name,int posx,int posy,int dtype)
    {
        this.lx=lx;
        this.ly=ly;
        this.icon=icon;
        this.name=name;
        this.posx=posx;
        this.posy=posy;
        this.dtype=dtype;
    }

}
