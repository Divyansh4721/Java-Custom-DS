import java.util.ArrayList;

class MapNode<K, V> {
    K key;
    V value;
    MapNode<K, V> next;

    public MapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class Hashmap<K, V> {
    ArrayList<MapNode<K, V>> buckets;
    int size;
    int numBuckets;

    Hashmap() {
        numBuckets = 5;
        buckets = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(null);
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % numBuckets;
    }

    public double loadfactor() {
        return (1.0 * size) / numBuckets;
    }

    public void rehash() {
        System.out.println("Rehash");
        ArrayList<MapNode<K, V>> temp = buckets;
        buckets = new ArrayList<>();
        for (int i = 0; i < 2 * numBuckets; i++) {
            buckets.add(null);
        }
        size = 0;
        numBuckets *= 2;
        for (int i = 0; i < temp.size(); i++) {
            MapNode<K, V> head = temp.get(i);
            while (head != null) {
                K key = head.key;
                V value = head.value;
                insert(key, value);
                head = head.next;
            }
        }
    }

    public void insert(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = buckets.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = buckets.get(bucketIndex);
        MapNode<K, V> newEle = new MapNode<K, V>(key, value);
        newEle.next = head;
        buckets.set(bucketIndex, newEle);
        if (loadfactor() > 0.7) {
            rehash();
        }
    }

    public int size() {
        return size;
    }

    public V search(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = buckets.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        MapNode<K, V> head = buckets.get(bucketIndex);
        MapNode<K, V> previous = null;
        while (head != null) {
            if (head.key.equals(key)) {
                size--;
                if (previous == null) {
                    buckets.set(bucketIndex, head.next);
                    return head.value;
                } else {
                    previous.next = head.next;
                    return head.value;
                }
            }
            previous = head;
            head = head.next;
        }
        return null;
    }

}
