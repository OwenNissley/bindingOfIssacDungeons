import java.util.ArrayList;
import java.util.Random;

public class easyRoom extends room {
    private ArrayList<tile> easyTiles;

    easyRoom() {
        //MUST HAVE MORE MONSTERS THAN ITEMS FOR RANDOMIZER TO WORK
        super(3, 3, 2);
    }
}
