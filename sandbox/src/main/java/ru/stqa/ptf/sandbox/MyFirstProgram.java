package ru.stqa.ptf.sandbox;

public class MyFirstProgram {

  public static void main (String[] args) {
    hello("world");
    hello("user");
    hello("Evgeniya");

    Square s=new Square(5);
    //s.l=5;
      System.out.println("Площадь квадрата со стороной "+s.l+" = "+s.area());

    Rectangle r=new Rectangle(4,6);

    /*r.a=4;
    r.b=6;*/
      System.out.println("Площадь прямоугольника со сторонами "+r.a+" и "+r.b+" = "+r.area());

      Point p1 = new Point(2, 2);
      System.out.println("Координата x= "+p1.x);
      Point p2 = new Point(0,0);
      System.out.println("Расстояние между точками равно "+p1.distance(p2));
}

  public static void hello(String somebody) {

     System.out.println("Hello, "+somebody+"!");
  }

}

