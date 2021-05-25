package example;

import javafx.scene.paint.Paint;

public class Disk {
    Paint color;
    Integer width;
    Integer height;

    Disk(Paint color, Integer width, Integer height)
    {
        this.color = color;
        this.width = width;
        this.height = height;
    }


    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Paint getColor() {
        return color;
    }
}
