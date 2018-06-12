/*
    Created by Timothy Garrett
    6/6/18
 */
using System;

class Randomizer{
    private int from;
    private int to;
    private int seed;
    public Randomizer(int from, int to, int seed){
        this.from = from;
        this.to = to;
        this.seed = seed;
    }
    
    public int From{
        get{return from;}
        set{from = value;}
    }
    public int To{
        get{return to;}
        set{to = value;}
    }

    public int Seed{
        get{return seed;}
        set{seed = value;}
    }
    public int RandomValue() => new Random(seed).Next(from, to);

    public char[] RandomChar(int length){
        Random rand = new Random(seed);
        char[] output = new char[length];
        for(int i = 0; i < length; i++)
            output[i] = (char)rand.Next(97,123);
        return output;
    }
}