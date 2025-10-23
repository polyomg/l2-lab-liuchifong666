package poly.edu.model;

public class HinhChuNhat {
    private double width;
    private double height;
    private double area;
    private double perimeter;

    public HinhChuNhat(double width, double height) {
        this.width = width;
        this.height = height;
        this.area = width * height;
        this.perimeter = 2 * (width + height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }
}
