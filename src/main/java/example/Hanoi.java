package example;

import java.util.ArrayList;

public class Hanoi {
    Integer numberDisks;
    ArrayList<Tower> towers;

    Hanoi(Integer numberDisks){
        setNumberDisks(numberDisks);
        towerInit();
    }

    public void setNumberDisks(Integer numberDisks) {
        this.numberDisks = numberDisks;
        towerInit();
    }

    public Integer getNumberDisks() {
        return numberDisks;
    }

    private void towerInit(){
        towers = new ArrayList<>();
        towers.add(new Tower(numberDisks, 120, 40));
        towers.add(new Tower());
        towers.add(new Tower());
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void moveDisk(int from, int to){
        Disk d = towers.get(from).front();
        if(d == null)
            return;
        if (towers.get(to).push(d)){
            towers.get(from).pop();
        }
    }
}
