import java.util.*;

class minPQ {

    private ArrayList<Integer> heap;

    public minPQ() {
        heap = new ArrayList<Integer>();
    }

    public minPQ(ArrayList<Integer> arr) {
        // minPQ pq = new minPQ(new ArrayList<>(List.of(1,2,3)));
        heap = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++) {
            this.insert(arr.get(i));
        }
    }

    boolean isEmpty() {
        return heap.size() == 0;
    }

    int getSize() {
        return heap.size();
    }

    int getMin() {
        if (isEmpty()) {
            return -1;
        }
        return heap.get(0);
    }

    void printHeap() {
        if (isEmpty()) {
            return;
        }
        printBook.printHeapList(heap, 0);
    }

    void insert(int element) {
        heap.add(element);
        int lastindex = heap.size() - 1;
        while (lastindex != 0 && heap.get(lastindex) < heap.get((lastindex - 1) / 2)) {
            Collections.swap(heap, lastindex, (lastindex - 1) / 2);
            lastindex = (lastindex - 1) / 2;
        }
        // printBook.printHeapList(heap, 0);
    }

    int removeMin() {
        if (isEmpty()) {
            return -1;
        }
        int ans = heap.get(0);
        Collections.swap(heap, 0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        int firstindex = 0;
        int left = (firstindex * 2) + 1;
        int right = (firstindex * 2) + 2;
        while (left < heap.size()) {
            int min = heap.get(left) < heap.get(firstindex) ? left : firstindex;
            min = right < heap.size() && heap.get(right) < heap.get(min) ? right : min;
            if (min == firstindex)
                break;
            Collections.swap(heap, min, firstindex);
            firstindex = min;
            left = (firstindex * 2) + 1;
            right = (firstindex * 2) + 2;
        }
        // printBook.printHeap2D(heap, 0);
        return ans;
    }

}