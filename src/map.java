import java.util.LinkedList;
import java.util.Queue;

public class map {
    //Keep tract of rooms
    private Queue<room> rooms;

    /**
     * Will generate list of rooms via newRoom and fill Queue
     */
    map(String difficulty, int size){
        rooms = new LinkedList<>();
        if(difficulty.equals("easy")){
            for(int i=0; i<size; i++){
                rooms.add(makeEasyRoom());
            }
        }//else if(difficulty.equals("medium")){

       // }else {

       // }
    }

    /**
     * Supply next room for list
     * @return
     */
   public room makeEasyRoom(){
       return new easyRoom();
   }
    public room makeMediumRoom(){
       return null;

    }
    public room makeHardRoom(){
       return null;

    }

    public room getNextRoom(){
       return rooms.remove();
    }


}
