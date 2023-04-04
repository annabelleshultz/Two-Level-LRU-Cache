public class LevelNode{
    private String LevelName;
    private LRUCache memory;
    private LevelNode upper;
    private LevelNode lower;
    public LevelNode(Level upper, Level lower, int size, String name){

        memory = new LRUCache(size);
        LevelName = name;
    }

    public void add(Integer data) {
        /*
        add to LRU
        see if over flow
                if add to lower
         */
        Integer toBeMoved = memory.refer(data);
        if(toBeMoved != null && lower != null){
             lower.add(toBeMoved);
        }
    }


    public Integer findData(int data, int depth) {
        Integer foundData = memory.getValue(data);
        if(foundData == null) {
            if (lower != null) {
                // if not found go lower
                Integer foundDataLower = getLowerLevel().findData(data, depth+1);
                if(foundDataLower != null){
                    addUpper(depth,foundDataLower);
                }
                return foundData;
            }
            return null;
        }
        return foundData;
    }

    public  void addUpper(int depth, Integer data){
        if(depth == 0){
            add(data);
            return;
        }
        getUpperLevel().addUpper(depth-1,data);
    }

    LevelNode getUpperLevel(){
        return upper;
    }

    LevelNode getLowerLevel(){
        return lower;
    }

    public void setLower(LevelNode lower) {
        this.lower = lower;
    }

    public void setUpper(LevelNode upper) {
        this.upper = upper;
    }

    @Override
    public String toString() {
        return "LevelNode{" +
                "LevelName='" + LevelName + '\'' +
                ", memory=" + memory +
                '}';
    }
}
