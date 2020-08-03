public class Design_HashSet{
    LinkedList<Integer>[] bucket = null;
    int cap = 1000;
    double loadFactor = 0.75;
    int count = 0;
    /** Initialize your data structure here. */
    public MyHashSet() {
        bucket = new LinkedList[cap];
    }
    
    public void add(int key) {
        if(contains(key))
            return;
        if(loadFactor*cap == count){
            count = 0;
            cap *= 2;
            List<Integer> oldBucket[] = bucket;
            bucket = new LinkedList[cap];
            for(int i = 0; i < oldBucket.length; i++){
                List<Integer> temp = oldBucket[i];
                if(temp != null){
                    for(int val : temp){
                        this.add(val);
                    }
                }
            }
        }
        int hash = key%cap;
        if(bucket[hash] == null)
            bucket[hash] = new LinkedList<>();
        bucket[hash].add(key);
        count++;
    }
    
    public void remove(int key) {
        int hash = key % cap;
        List<Integer> temp = bucket[hash];
        if(temp != null){
            Iterator<Integer> itr = temp.iterator();
            while(itr.hasNext()){
                if(itr.next() == key){
                    itr.remove();
                    count--;
                }
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = key % cap;
        List<Integer> temp = bucket[hash];
        if(temp != null){
            Iterator<Integer> itr = temp.iterator();
            while(itr.hasNext()){
                if(itr.next() == key){
                    return true;
                }
            }
        }
        return false;
    }
}