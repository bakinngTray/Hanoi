package example;


import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HanoiView {
    Hanoi hanoi;
    Group mainBoard = new Group();

    public void setHanoi(Hanoi hanoi) {
        this.hanoi = hanoi;
        drawGroup();
    }

    public void drawRefresh(){
        drawGroup();
    }

    private void drawGroup(){
        mainBoard.getChildren().clear();

        Rectangle background = new Rectangle(0, 0, 470, 470);
        background.setFill(Paint.valueOf("#f4f4f4"));

        Rectangle kernel1 = new Rectangle(70, 100, 10, 210);
        Rectangle kernel2 = new Rectangle(230, 100, 10, 210);
        Rectangle kernel3 = new Rectangle(390, 100, 10, 210);

        mainBoard.getChildren().addAll(background, kernel1, kernel2, kernel3);

        for(int i = 0; i < 3; ++i){
            Tower t = hanoi.getTowers().get(i);
            for(int j = 0; j < t.getStack().size(); ++j){
                Disk d = t.getStack().get(j);
                int y = 300 - j*d.getHeight();
                int x = 70 + 160 * i + 5 - d.getWidth()/2;
                Rectangle rec = new Rectangle(x, y, d.getWidth(), d.getHeight());
                rec.setFill(d.getColor());
                rec.setArcHeight(20.0);
                rec.setArcWidth(20.0);
                mainBoard.getChildren().add(rec);
            }
        }
    }

    public Group getMainBoard() {
        return mainBoard;
    }

    HanoiView(Hanoi hanoi){
        setHanoi(hanoi);
        drawGroup();
    }
}
