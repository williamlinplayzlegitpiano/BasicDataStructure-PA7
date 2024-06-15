import java.util.ArrayList;

public class MyAlgorithm {
    
    public static Integer lastAtom (ArrayList<Integer> atoms) {
        if (atoms == null) {
            throw new NullPointerException();
        }

        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(atoms);

        while (queue.getLength() > 1) {
            int first = queue.pop();
            int second = queue.pop();
            int newAtom;
            if (first == second) {
                newAtom = first + second;
            } else {
                newAtom = Math.abs(first - second);
            }
            queue.push(newAtom);
        }

        return queue.pop();

    }


}
