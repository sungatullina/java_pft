package ru.stqa.ptf.sandbox;

/**
 * Created by Sungatullina on 16.03.2017.
 */
public class Point {

  public double x;
  public double y;

  public Point(double x, double y){
    this.x=x;
    this.y=y;
  }

  public double distance(Point p){
    return Math.sqrt(Math.pow(p.x-this.x,2)+Math.pow(p.y-this.y,2));

  }

 /* public static double distance(Point a, Point b){

    return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
  }*/
}
