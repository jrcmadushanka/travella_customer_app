package com.example.travella;

public class Score {
    int id;
    int score;
    String name;

    public Score(){   }
    public Score(int id, int token, String journey){
        this.id = id;
        this.score = token;
        this.name = journey;


    }

    public Score( int score, String name){
        this.score = score;
        this.name = name;


    }

    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name= name;
    }

}
