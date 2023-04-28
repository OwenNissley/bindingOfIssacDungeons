public class itemTile extends tile{

    item item;
    /**
     * Constructor to supply symbol
     */
    itemTile(item item){
        super(item.getSymbol());
        this.item = item;
        super.setDisplaySymbol("?");
    }

    @Override
    public item getItem() {
        return item;
    }
}
