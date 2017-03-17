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

  public static double distance(Point a, Point b){

    return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
  }
}
