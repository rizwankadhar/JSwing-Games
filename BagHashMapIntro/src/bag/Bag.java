/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bag;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bli
 */
public class Bag {
    HashMap<String, Integer> data;

    public Bag() {
        data = new HashMap<>();
    }
    
    public void add(String item, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        if (!data.containsKey(item)) {
            data.put(item, num);
        } else {
            data.put(item, data.get(item) + num);
        }
    }
    
    public boolean contains(String item) {
        return data.containsKey(item);
    }
    
    public Integer remove(String item) {
        return data.remove(item);
    }
    
    public boolean remove(String item, int num) {
        if (!data.containsKey(item)) {
            return false;
        } else {
            int left = data.get(item) - num;
            if (left > 0) {
                data.put(item, left);
            } else {
                data.remove(item);
            }
            return true;
        }
    }
    
    public int howMany(String item) {
        Integer num = data.get(item);
        return num != null ? num : 0;
    }
    
    public boolean isEmpty() {
        return data.isEmpty();
    }
    
    public void clear() {
        data.clear();
    }
    
    public Bag union(Bag bag) {
        Bag result = new Bag();
        for (Map.Entry<String, Integer> entry : bag.data.entrySet()) {
            result.add(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : this.data.entrySet()) {
            result.add(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    public Bag intersection(Bag bag){
        Bag result = new Bag();
        for (Map.Entry<String, Integer> entry : bag.data.entrySet()){
            if (data.containsKey(entry.getKey())){
                int value = howMany(entry.getKey()) < entry.getValue() ? howMany(entry.getKey()) : entry.getValue(); 
                result.add(entry.getKey(), value);
            }
        }
        return result;
    }
    // Implement intersection and difference here
    public Bag difference(Bag bag) throws Exception{
        Bag result = new Bag();
        for (Map.Entry<String, Integer> entry : data.entrySet()){
            if (!bag.data.containsKey(entry.getKey())){
                result.add(entry.getKey(), entry.getValue());
            }else if (bag.data.containsKey(entry.getKey())){
                if (bag.howMany(entry.getKey()) > entry.getValue()){
                    throw new IllegalArumentException();
                }
                result.add(entry.getKey(), entry.getValue() - bag.howMany(entry.getKey()));
            }
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "Bag{" + "data=" + data + '}';
    }
}
