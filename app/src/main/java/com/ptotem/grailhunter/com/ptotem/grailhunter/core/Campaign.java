package com.ptotem.grailhunter.com.ptotem.grailhunter.core;

/**
 * Created by vikram on 05/08/14.
 */
public class Campaign
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCostMin() {
        return costMin;
    }

    public void setCostMin(int costMin) {
        this.costMin = costMin;
    }

    public int getCostMax() {
        return costMax;
    }

    public void setCostMax(int costMax) {
        this.costMax = costMax;
    }

    public int getPayoffMin() {
        return payoffMin;
    }

    public void setPayoffMin(int payoffMin) {
        this.payoffMin = payoffMin;
    }

    public int getPayoffMax() {
        return payoffMax;
    }

    public void setPayoffMax(int payoffMax) {
        this.payoffMax = payoffMax;
    }

    public String getCostCurrency() {
        return costCurrency;
    }

    public void setCostCurrency(String costCurrency) {
        this.costCurrency = costCurrency;
    }

    public String getPayoffCurrency() {
        return payoffCurrency;
    }

    public void setPayoffCurrency(String payoffCurrency) {
        this.payoffCurrency = payoffCurrency;
    }

    private String name, icon, description;
    private int id;
    private int costMin, costMax, payoffMin, payoffMax;
    private String costCurrency, payoffCurrency;

    public Campaign(int id, String name, String icon, String des, int costmin, int costmax, String costCurrency, int payoffmin, int payoffmax, String payoffCurrency) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        description = des;
        this.costMin = costmin;
        this.costMax = costmax;
        this.costCurrency = costCurrency;
        this.payoffMin = payoffmin;
        this.payoffMax = payoffmax;
        this.payoffCurrency = payoffCurrency;
    }
}