package com.example.oussama.taxis;

public class course {

public double LatitudePosition;
public double LongitudePosition;
public double LatitudeDistination;
public double LongitudeDistination;
int prix ;
int distance ;
int numtel ;
public course() {}

public  course (double l1 , double lg1 , double l2 , double lg2 , int pr , int dist  , int numtel  ) {

LatitudePosition=l1 ;
LongitudePosition=lg1 ;
LatitudeDistination= l2 ;
LongitudeDistination=lg2 ;
prix=pr;
distance= dist  ;
 this.numtel=numtel ;
}

 public void setPrix(int prix) {
        this.prix = prix;
    }


   public int getprix()
    {
        return prix ;
    }

   public void setDistance(int distance ) {

    this.distance=distance ;

   }

   public int getDistance(){

    return distance ;

   }


    public void setNumtel(int numtel ) {

        this.numtel=numtel ;

    }

    public int getNumtel(){

        return numtel ;

    }



}
