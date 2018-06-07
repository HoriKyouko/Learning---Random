/*
    Created by Timothy Garrett
    6/6/18
 */
using System;

class Randomizer{
    private int from;
    private int to;
    public Randomizer(){}
    public Randomizer(int from, int to){
        this.from = from;
        this.to = to;
    }
    
    public int From{
        get{return from;}
        set{from = value;}
    }
    public int To{
        get{return to;}
        set{to = value;}
    }
    public int RandomValue() => new Random().Next(from, to);
}