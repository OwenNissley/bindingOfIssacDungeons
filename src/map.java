import java.util.LinkedList;
import java.util.Queue;

public class map {
    //Keep tract of rooms
    private Queue<room> rooms;

    /**
     * Will generate list of rooms via newRoom and fill Queue
     */
    map(String difficulty, int size) {
        rooms = new LinkedList<>();
        if (difficulty.equals("easy")) {
            for (int i = 0; i < size; i++) {
                rooms.add(new easyRoom());
            }
        } else if (difficulty.equals("medium")) {
            for (int i = 0; i < size; i++) {
                //rooms.add( new mediumRoom()); - etc. for hard rooms...
            }
        }
    }
    public room getNextRoom(){
       return rooms.remove();
    }

}
