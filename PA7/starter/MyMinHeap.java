import java.util.ArrayList;
import java.util.Collection;

public class MyMinHeap <E extends Comparable<E>> implements MinHeapInterface<E> {
    
    protected ArrayList<E> data;

    public MyMinHeap() {
        data = new ArrayList<>();
    }

    public MyMinHeap(Collection<? extends E> collection) {

        if (collection == null) {
            throw new NullPointerException();
        }

        this.data = new ArrayList<>(collection);

        for (E element : data) {
            if (element == null) {
                throw new NullPointerException();
            }
        }

        for (int i = getParentIdx(data.size() - 1); i >= 0; i--) {
            percolateDown(i);
        }

    }

    protected void swap (int from, int to) {
        E temp = (data.get(from));
        this.data.set(from, data.get(to));
        this.data.set(to, temp);
    }

    protected static int getParentIdx(int index) {
        if (index % 2 == 0) {
            return (index - 2)/2;
        } else {
            return (index - 1)/2;
        }

    }

    protected static int getLeftChildIdx(int index) {
        return 2 * index + 1;
    }

    protected static int getRightChildIdx(int index) {
        return 2 * index + 2;
    }

    protected int getMinChildIdx(int index) {
        int leftChildIdx = getLeftChildIdx(index);
        int rightChildIdx = getRightChildIdx(index);
        
        if (leftChildIdx >= this.data.size()) {
            return -1;
        }

        if (rightChildIdx >= this.data.size()) {
            return leftChildIdx;
        }

        if (this.data.get(leftChildIdx).compareTo(this.data.get(rightChildIdx)) <= 0) {
            return leftChildIdx;
        } else {
            return rightChildIdx;
        }

    }

    protected void percolateUp(int index) {

        E element = this.data.get(index);
        while (index > 0) {
            int parentIndex = getParentIdx(index);
            E parent = this.data.get(parentIndex);
            if (element.compareTo(parent) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }

    }

    protected void percolateDown(int index) {

            int minChildIdx = 0;
            while ((minChildIdx = getMinChildIdx(index)) != -1) {
                if (this.data.get(index).compareTo(this.data.get(minChildIdx)) <= 0) {
                    break;
                }

                swap(index, minChildIdx);
                index = minChildIdx;

            }

    }


    protected E deleteIndex(int index) {

        if (index >= this.data.size()) {
            throw new IndexOutOfBoundsException();
        }

        E removedElement = this.data.get(index);
        if (index == this.data.size() - 1) {
            this.data.remove(index);
        } else {
            E lastElement = this.data.remove(this.data.size() - 1);
            this.data.set(index, lastElement);
            if (index > 0 && this.data.get(index).compareTo(this.data.get(getParentIdx(index))) < 0) {
                percolateUp(index);
            } else {
                percolateDown(index);
            }
        }
        return removedElement;
    }

    public void insert(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        this.data.add(element);
        percolateUp(this.data.size() - 1);
    }

    public E getMin() {
        if (this.data.isEmpty()) {
            return null;
        }
        return this.data.get(0);
    }

    public E remove() {
        if (this.data.isEmpty()) {
            return null;
        }
        return deleteIndex(0);
    }

    public int size() {
        return this.data.size();
    }

    public void clear() {
        this.data.clear();
    }

}
