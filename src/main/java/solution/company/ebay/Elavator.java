package solution.company.ebay;

import java.util.ArrayList;
import java.util.List;

///  电梯
public class Elavator {
    private int direction = 0; // 0  up， 1 down
    private List<Integer> upfloorList = new ArrayList<Integer>(); //
    private List<Integer> downfloorList = new ArrayList<Integer>(); ///

    private int capacity;
    private int topFloor;
    private int bottomFloor;
    private int currentFloor;

    public void up() {
         this.direction = 0;
    }

    public void down() {
        this.direction = 1;
    }

    public void setFloor(int floor) {
        this.currentFloor = floor;
    }
}
