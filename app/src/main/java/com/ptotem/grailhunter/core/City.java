package com.ptotem.grailhunter.core;

import android.widget.Button;

/**
 * Created by vikram on 05/08/14.
 */
public class City
{
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

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getDtype() {
        return dtype;
    }

    public void setDtype(int dtype) {
        this.dtype = dtype;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }


    private int lx,ly,posx,icon,posy,dtype;
    private String name;
    private Button button;
    public City(int lx,int ly,int icon,String name,int posx,int posy,int dtype,Button button)
    {
        this.lx=lx;
        this.ly=ly;
        this.icon=icon;
        this.name=name;
        this.posx=posx;
        this.posy=posy;
        this.dtype=dtype;
        this.button=button;
    }

}
