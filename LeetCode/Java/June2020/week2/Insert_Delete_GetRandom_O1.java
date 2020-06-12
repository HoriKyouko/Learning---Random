public class Insert_Delete_GetRandom_O1 {
    ArrayList<Integer> arr;
    HashMap<Integer, Integer> map;
    Random rnd;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        arr = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
        rnd = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;
        map.put(val, arr.size());
        arr.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        int idx = map.get(val);
        if(idx < arr.size()-1){
            int last = arr.get(arr.size()-1);
            map.put(last, idx);
            arr.set(idx, last);
        }
        map.remove(val);
        arr.remove(arr.size()-1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return arr.get(rnd.nextInt(arr.size()));
    }
}