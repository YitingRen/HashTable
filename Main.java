import java.util.ArrayList;

// A node of chains 
class HashNode<K, V>
{
    K key;
    V value;

    HashNode<K, V> next;
    // Constructor 
    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}

// Class to represent entire hash table 
class Map_Test<K, V>
{
    // bucketArray is used to store array of chains 
    private ArrayList<HashNode<K, V>> bucketArray;

    // Current capacity of array list 
    private int capacity;

    // Current size of array list 
    private int size;

    // Constructor (Initializes capacity, size and 
    // empty chains. 
    public Map_Test()
    {
        bucketArray = new ArrayList<>();
        capacity = 5;
        size = 0;

        // Create empty chains 
        for (int i = 0; i < capacity; i++)
            bucketArray.add(null);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    // This implements hash function to find index 
    // for a key 
    private int getBucketIndex(K key)
    {
        //int hashCode = key.hashCode();

        String str= (String) key;
        int index = (int)( (str.length()+7) % capacity + 0.5);
        //System.out.println("   "+index);
        return index;
    }

    // Method to remove a given key 
    public V remove(K key)
    {
        // Apply hash function to find index for given key 
        int bucketIndex = getBucketIndex(key);

        // Get head of chain 
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // Search for key in its chain 
        HashNode<K, V> prev = null;
        while (head != null)
        {
            // If Key found 
            if (head.key.equals(key))
                break;

            // Else keep moving in chain 
            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;
        size--;

        // Remove key 
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);

        return head.value;
    }

    // Returns value for a key 
    public V get(K key)
    {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head =
                bucketArray.get(bucketIndex);

        // Search key in chain 
        while (head != null)
        {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        return null;
    }

    // Adds a key value pair to hash 
    public void add(K key, V value)
    {
        int bucketIndex = getBucketIndex(key);

        //System.out.println(bucketIndex);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        // Check if key is already present 
        while (head != null)
        {
            if (head.key.equals(key)) {
                head.value = value;
                return; }
            head = head.next;
        }
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);
        if (size>=3*capacity)//扩容
        {
              bucketArray=resize(capacity,bucketArray);
        }
    }

    private ArrayList<HashNode<K, V>> resize(int capacity, ArrayList bucketArray) {

        ArrayList<HashNode<K, V>> temp=bucketArray;
        for(int i=0;i<capacity;i++){
            temp.add(null);
        }
        return temp;

    }
    // Driver method to test Map class 
    public static void main(String[] args)
    {
        Map_Test<String, Integer>map = new Map_Test<>();
        map.add("this",1 );
        map.add("coder",2 );
        map.add("this9",4 );
        map.add("hi",5 );
        map.add("this1",101);
        map.add("coder2",2 );
        map.add("thisk",4 );
        map.add("hik",5 );
        map.add("this1k",101);
        map.add("this3",1 );
        map.add("this4",1 );
        map.show();
    }

    private void show() {
        for(int i=0;i<bucketArray.size();i++){
            HashNode node= bucketArray.get(i);
            System.out.println("--------------------------------------");
            if(node!=null){
                while(node!=null){
                    System.out.print((String)node.key+"  ");
                    node = node.next;
                }
            }System.out.println();
        }
        System.out.println("--------------------------------------");
    }
} 