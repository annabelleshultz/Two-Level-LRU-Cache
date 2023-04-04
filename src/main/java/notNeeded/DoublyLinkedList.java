package notNeeded;/*
Annabelle Shultz 
LRU Cache System Using Hashtable and Double Linked List using Java
Spring 2022

*/

import java.util.HashMap;

public class DoublyLinkedList {
    class DLinkedNode {
        String key;
        String val;
        DLinkedNode next_node;
        DLinkedNode prev_node;
        public DLinkedNode prev;
		public DLinkedNode next;
		
        
        public DLinkedNode(String key, String value) {
            this.key = key;
            val = value;
        }
    }
    private HashMap<String, DLinkedNode> map = new HashMap<String,DLinkedNode>();
    private int len;
    private DLinkedNode head;
    private DLinkedNode tail;
    private int capacity;

    public DoublyLinkedList(int capacity) {
        this.capacity = capacity;
        len = 0;
    }

    public void removeNode(DLinkedNode node) {
        DLinkedNode prev_node = node.prev;
        DLinkedNode new_node = node.next;

        if (prev_node != null) {
            prev_node.next = new_node;
        }
        else {
            head = new_node;
        }
        if (new_node != null) {
            new_node.prev = prev_node;
        }
        else {
            tail = prev_node;
        }
    }

    public void setHead(DLinkedNode node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }

        head = node;
        if(tail == null); {
            tail = node;
        }
    }

    public String get(String key) {
        if (map.containsKey(key)) {
            DLinkedNode latest = map.get(key);
            removeNode(latest);
            return latest.val;
        }
        else return "-1";
    }

    public void put(String key, String value) {
        if(map.containsKey(key)) {
            DLinkedNode oldNode = map.get(key);
            oldNode.val = value;
            removeNode(oldNode);
            setHead(oldNode);
        }
        else {
            DLinkedNode newNode = new DLinkedNode(key, value);
            if (len<capacity) {
                setHead(newNode);
                map.put(key, newNode);
                len++;
            }else{
                map.remove(tail.key);
                tail = tail.prev;
                if (tail != null) {
                    tail.next = null;
                }
                setHead(newNode);
                map.put(key, newNode);
            }
        }
    }
}

/* 
main program of paging-policy.py

parser = OptionParser()
parser.add_option('-a', '--addresses', default='-1',   help='a set of comma-separated pages to access; -1 means randomly generate',  action='store', type='string', dest='addresses')
parser.add_option('-f', '--addressfile', default='',   help='a file with a bunch of addresses in it',                                action='store', type='string', dest='addressfile')
parser.add_option('-n', '--numaddrs', default='10',    help='if -a (--addresses) is -1, this is the number of addrs to generate',    action='store', type='string', dest='numaddrs')
parser.add_option('-p', '--policy', default='FIFO',    help='replacement policy: FIFO, LRU, OPT, UNOPT, RAND, CLOCK',                action='store', type='string', dest='policy')
parser.add_option('-b', '--clockbits', default=2,      help='for CLOCK policy, how many clock bits to use',                          action='store', type='int', dest='clockbits')
parser.add_option('-C', '--cachesize', default='3',    help='size of the page cache, in pages',                                      action='store', type='string', dest='cachesize')
parser.add_option('-m', '--maxpage', default='10',     help='if randomly generating page accesses, this is the max page number',     action='store', type='string', dest='maxpage')
parser.add_option('-s', '--seed', default='0',         help='random number seed',                                                    action='store', type='string', dest='seed')
parser.add_option('-N', '--notrace', default=False,    help='do not print out a detailed trace',                                     action='store_true', dest='notrace')
parser.add_option('-c', '--compute', default=False,    help='compute answers for me',                                                action='store_true', dest='solve')

(options, args) = parser.parse_args()

print('ARG addresses', options.addresses)
print('ARG addressfile', options.addressfile)
print('ARG numaddrs', options.numaddrs)
print('ARG policy', options.policy)
print('ARG clockbits', options.clockbits)
print('ARG cachesize', options.cachesize)
print('ARG maxpage', options.maxpage)
print('ARG seed', options.seed)
print('ARG notrace', options.notrace)
print('')

addresses   = str(options.addresses)
addressFile = str(options.addressfile)
numaddrs    = int(options.numaddrs)
cachesize   = int(options.cachesize)
seed        = int(options.seed)
maxpage     = int(options.maxpage)
policy      = str(options.policy)
notrace     = options.notrace
clockbits   = int(options.clockbits)

random_seed(seed)
*/
