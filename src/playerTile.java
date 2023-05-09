import org.w3c.dom.css.CSSUnknownRule;

public class playerTile extends tile{
    private player player;

    private int posR;

    private int posC;
    /**
     * @param symbol Stores symbol to be used in the game.
     */
    playerTile(String symbol) {
        super(symbol);
        player = new player();
        posC =0;
        posR =0;
        this.setSymbol(symbol);
    }


    public player getPlayer() {
        return player;
    }

    public void updatePosC(int posC) {
        this.posC = posC;
    }

    public void updatePosR(int posR) {
        this.posR = posR;
    }

    public int getPosC() {
        return posC;
    }

    public int getPosR() {
        return posR;
    }

}
