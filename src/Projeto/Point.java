package Projeto;


import java.util.List;
import java.util.Objects;

public class Point {

//  public Integer x;

  // public Integer y;

  public List<Nodes> nodes;
  public List<Pol> pol;

  public float x;

  public float y;

  public float distX(Point p) {
    return Math.abs(this.x - p.x);
  }

  public float distY(Point p) {
    return Math.abs(this.y - p.y);
  }

  public float dist(Point p) {
    return Math.abs(distX(p) + distY(p));
  }

  public void moveX(float delta) {
    System.out.println("Moving " + delta + ", to the value -> " + this.x);
    this.x += delta;
    System.out.println("Value moved -> " + this.x);
  }

  public void moveY(float delta) {
    System.out.println("Moving " + delta + ", to the value -> " + this.y);
    this.y += delta;
    System.out.println("Value moved -> " + this.y);
  }

  public void move(float x, float y) {
    System.out.println("Moving " + x + ", to the value -> " + y);
    System.out.println("Value moved -> " + (x + y));
  }

  public void printPoint() {
    System.out.println("Point -> " + this.x + this.y);
  }


  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "Point{" +
            "x=" + x +
            ", y=" + y +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return Float.compare(point.x, x) == 0 && Float.compare(point.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public void main(String[] args) {
  }
}
