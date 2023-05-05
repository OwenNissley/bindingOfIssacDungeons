import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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
                rooms.add(new mediumRoom());
            }
        }else if (difficulty.equals("hard")) {
            for (int i = 0; i < size; i++) {
                rooms.add(new hardRoom());
            }
        } else if (difficulty.equals("17543")) { //secert super hard mode
            for (int i = 0; i < size; i++) {
                rooms.add(new unbeatableRoom());
            }
        }else if (difficulty.equals("mixed")) {
            for (int i = 0; i < size; i++) {
                rooms.add(this.getRandomRoom());
            }
        }
    }
    public room getNextRoom(){
       return rooms.remove();
    }

    public room getRandomRoom(){
        Random r = new Random();
        int rand = r.nextInt(100);
        if(rand<50){
            return new easyRoom();
        }else {
            if(rand<70){
                return new mediumRoom();
            }else return new hardRoom();
        }
    }
}
