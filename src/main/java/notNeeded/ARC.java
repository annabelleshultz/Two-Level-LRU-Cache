package notNeeded;

import java.util.*;


public class ARC {
    private int p;
    HashMap<Integer, Node> data = new HashMap<>();
    //may need to make our own node system
    private Node T1;
    private Node T2;
    private Node B1;
    private Node B2;

    private int T1Size = 0;
    private int T2Size = 0;
    private int B1Size = 0;
    private int B2Size = 0;

    private int maxSizeOfCache = 0;

    public ARC(int size){
        maxSizeOfCache = size;
    }


    private void hitOnB1 () {
    }
    private void hitOnB2 () {
    }
    private void hitsOnT() {
    }
    private void misses(int key, Pair val) {
//        A cache miss has occurred. Now, one and only one
//        of the two cases must occur.
//        Case A: L1 has exactly = c pages.
//                Delete the LRU page in L1 to make room
//                for the new page, and make xt the MRU
//                page in L1.
//        Case B: L1 has less than = c pages.
//                1) If the cache is full, that is, (|L1| + |L2|) = 2c,
//                then delete the LRU page
//                in L2 to make room for the new page.
//                2) Insert xt as the MRU page in L1


        //Todo may need a new node
        // typeOfData varName = Value
        Node n = new Node(key, val);
        int L1 =  (T1Size + B1Size);
        int L2 = (T2Size + B2Size);

        if(L1 == maxSizeOfCache){
            if(T1Size < maxSizeOfCache) {
                /*
                    get the b1heads next value
                    remove that
                    adjust size
                 */
                Node toRemoved = B1.next;
                toRemoved.remove();
                B1Size--;

                //replace
                replace(n);

            } else if (T1Size >= maxSizeOfCache) {
                Node toRemoved = T1.next;
                toRemoved.remove();
                T1Size--;
            }
        } else if((L1 < maxSizeOfCache) && (L1 +L2) >= maxSizeOfCache){
            if((L1+L2) >= 2*maxSizeOfCache) {
                // todo
                Node toRemoved= (B2).next;
                toRemoved.remove();
                B2Size--;
                replace(n);
            }
        }
        T1Size++;
        data.put(key, n);
        n.addToEnd(T1);
    }

    private void replace(Node swap) {
        boolean isT1Empty = T1Size != 0;
        boolean isB2Type = swap.type == Type.B2;
        boolean exceedsP = T1Size>p;
        boolean equalsP = T1Size == p;
        if(!isT1Empty && (exceedsP || (isB2Type && equalsP))){
            //delete the LRU in page t1 move to b1
            Node removed = T1.next;
            removed.remove();

            removed.type = Type.B1;
            removed.addToEnd(B1);
            T1Size--;
            B1Size++;

        }else{
            //delete the LRU in page t2 move to b2
            Node removed = T2.next;
            removed.remove();

            removed.type = Type.B2;
            removed.addToEnd(B2);
            T2Size--;
            B2Size++;

        }

    }

    public void compute(ArrayList<Integer> input) {
            for (int i = 0; i < input.size(); i++) {
                refer(input.get(i));
        }
    }

    private void refer(Integer key) {
        Node node = data.get(key);
        if(node == null){
            // miss
        }else if(false){
            //hit on b1
            //node. type == notNeeded.Type.B2
        }else if(false){
            //hit on b2
        }else {
            // hit either t1 or t2
        }

    }
}

class Node{
    final int key;
    Node prev;
    Node next;
    Type type;
    Pair data;

    Node(int key, Pair data) {
        this.key = key;
        this.data = data;
    }

    Node() {
        this.key = Integer.MIN_VALUE;
        prev = this;
        next = this;
    }

    // may have to simplify
    public void addToEnd(Node head){
        Node tail = head.prev;
        head.prev = this;
        tail.next = this;
        next = head;
        prev = tail;
    }

    public void remove(){
        assert(key != Integer.MIN_VALUE);
        prev.next = next;
        next.prev = prev;
        prev = null;
        next = null;
        type = null;
    }

    //todo fix up
    @Override
    public String toString() {
        return "notNeeded.Node{" +
                "key=" + key +
                ", prev=" + prev +
                ", next=" + next +
                ", type=" + type +
                '}';
    }
}

class Pair{
    int id;
    int val;

    public Pair(int id, int val){
        this.id = id;
        this.val = val;
    }
}