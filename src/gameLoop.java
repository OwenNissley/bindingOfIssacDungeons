import java.util.Scanner;

public class gameLoop {
    public static void main(String[] args) throws Exception {
        room easyRoom = new easyRoom();
        while (true) {
            easyRoom.printRoom();
            String move = easyRoom.getMove();
            try{
                easyRoom.movePlayer(move);
            }catch (Exception e){
                System.out.println(e.getMessage() + " TRY AGAIN");
            }
        }
    }

}
