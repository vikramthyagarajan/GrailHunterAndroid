package com.ptotem.grailhunter.core;

/**
 * Created by vikram on 05/08/14.
 */
public class Question
{
    private int id,answer,payoff;
    private String name,opta,optb,optc,optd;

    public Question(int id,int payoff,String name,String opta,String optb,String optc,String optd,int answer)
    {
        this.id=id;
        this.payoff=payoff;
        this.name=name;
        this.opta=opta;
        this.optb=optb;
        this.optc=optc;
        this.optd=optd;
        this.answer=answer;
    }
    public boolean isAnswerCorrectByOption(int option)
    {
        if(this.answer==option)
            return true;
        else
            return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getPayoff() {
        return payoff;
    }

    public void setPayoff(int payoff) {
        this.payoff = payoff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpta() {
        return opta;
    }

    public void setOpta(String opta) {
        this.opta = opta;
    }

    public String getOptb() {
        return optb;
    }

    public void setOptb(String optb) {
        this.optb = optb;
    }

    public String getOptc() {
        return optc;
    }

    public void setOptc(String optc) {
        this.optc = optc;
    }

    public String getOptd() {
        return optd;
    }

    public void setOptd(String optd) {
        this.optd = optd;
    }
}
