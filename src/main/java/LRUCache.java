import notNeeded.DoublyLinkedList;

import java.util.*;

public class LRUCache {

    private final int capacity;
    private final HashMap<Object, Object> linkedListNodeMap;
    private final DoublyLinkedList doublyLinkedList;
    private Deque<Integer> doublyQueue;
	private HashSet<Integer> hashSet;
	private final int CACHE_SIZE;
    private static int misses = 0;
    private static int hits = 0;
    private String replaced = "-";

	LRUCache(int capacity) {
		doublyQueue = new LinkedList<>();
		hashSet = new HashSet<>();
		CACHE_SIZE = capacity;
        this.capacity = capacity;
        int maxSize = 5;
        this.linkedListNodeMap = new HashMap<>(maxSize);
        this.doublyLinkedList = new DoublyLinkedList(maxSize);
    }

    public void compute(ArrayList<Integer> input){
        for (int i = 0; i < input.size(); i++) {
            refer(input.get(i));
        }
    }

	public Integer refer(int page) {
        String missOrHit = "";
        Integer toBeMoved = null;
		int spaces = CACHE_SIZE*3+3;
        String queue = "[br ";
        if (!hashSet.contains(page)) {
            missOrHit = "MISS";
            misses++;
			if (doublyQueue.size() == CACHE_SIZE) {
				int last = doublyQueue.removeLast();
				hashSet.remove(last);
                replaced = String.valueOf(last);
                toBeMoved = last;
			}
		}
		else {
            missOrHit = "HIT";
            hits++;
			doublyQueue.remove(page);
		    toBeMoved = page;
        }
        // may need to change this
		doublyQueue.push(page);
		hashSet.add(page);
        Iterator iteratorVals = doublyQueue.descendingIterator();
        while (iteratorVals.hasNext()) {
            queue += String.valueOf(iteratorVals.next()) + ", ";
        }
        queue = queue.substring(0, queue.length() - 2) + "]";    
        queue = new String(new char[spaces - queue.length()]).replace('\0', ' ') + queue;

        String output = String.format("Access: %d %-4s LRU->%s<-MRU Replace:%s [br Hits:%d Misses:%d]", page, missOrHit, queue, replaced, hits, misses);
        System.out.println(output);
        return toBeMoved;
	}

    public Integer getValue (int data) {
        /*
        go through our LRU see if data exist from oldest to newest
        and remove the oldest value
        */
        // 0 -> n
        // oldest -> newest
        // front -> [...]<- rear
        Deque<Integer> newQueue = new LinkedList<>();

        /*
            1) while the queue is not empty we will proceed
                a) remove a value
                b) check if the removed  value is the value we are looking for
                    then stop the for loop
                c) if its is not add it to the other deque (other hand)
            2) add back the values
            3) return any data if it was found
         */

        boolean found = false;

        while (!doublyQueue.isEmpty()) {
            Integer currentVal = doublyQueue.removeFirst();
            if(currentVal != data) {
                newQueue.addFirst(currentVal);
            }
            else if (currentVal == data) {
                found = true;
                break;
            }
        }
        // take our newQueue values and add them back to og queue

        while (!newQueue.isEmpty()){
            Integer newValues = newQueue.removeFirst();
            doublyQueue.addFirst(newValues);
        }
        if(found){
            hashSet.remove(data);
            return data;
        }
        return null;
    }

    @Override
    public String toString() {
        String queue = "[";
        Iterator iteratorVals = doublyQueue.descendingIterator();
        while (iteratorVals.hasNext()) {
            queue += String.valueOf(iteratorVals.next()) + ", ";
        }
        queue = queue.substring(0,queue.lastIndexOf(","));
        queue += ']';
        return queue;

    }
}
