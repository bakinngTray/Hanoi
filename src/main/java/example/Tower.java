package example;

import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Tower {
    ArrayList<Disk> stack;
    final static String[] colors = {"Turquoise", "Pink", "Green", "Gray", "Red", "Yellow", "Blue", "Brown", "Purple", "Orange"};

    Tower(int numberDisks, int start, int stop){
        stack = new ArrayList<>();
        int step = 1;
        if (numberDisks != 0)
            step = (start - stop)/numberDisks;
        for(int i = 0; i < numberDisks; ++i){
            stack.add(new Disk(Paint.valueOf(colors[i]), start - step*i, 20));
        }
    }

    Tower(){
        stack = new ArrayList<>();
    }

    public ArrayList<Disk> getStack() {
        return stack;
    }

    public Disk front(){
        if (stack.size() == 0){
            return null;
        }
        else{
            return stack.get(stack.size() - 1);
        }
    }

    public Disk pop(){
        if (stack.size() == 0){
            return null;
        }
        else{
            return stack.remove(stack.size() - 1);
        }
    }

    public boolean push(Disk disk){
        if (front() == null || (front().getWidth() > disk.getWidth()))
        {
            stack.add(disk);
            return true;
        }
        return false;
    }
}
