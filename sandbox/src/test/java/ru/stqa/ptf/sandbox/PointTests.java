package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sungatullina on 31.03.2017.
 */
public class PointTests {

  @Test

  public void testDistance1(){
    Point p1 = new Point(2, 2);
    Point p2 = new Point(0,0);
    Assert.assertEquals(p1.distance(p2), 2.8284271247461903);

  }
  @Test
  public void testDistance2(){
    Point p1 = new Point(2, 2);
    Point p2 = new Point(0,0);
    Assert.assertNotEquals(p1.distance(p2), 10.0);
  }
  @Test
  public void testDistance3() {
    Point p = new Point(1050, 1050);

    Assert.assertEquals(p.distance(p), 0.0);
  }
  @Test
  public void testDistance4(){
    Point p1 = new Point(-2000, 0);
    Point p2 = new Point(0,-2000);
    Assert.assertEquals(p1.distance(p2), 2828.42712474619,0.001);
  }


}
