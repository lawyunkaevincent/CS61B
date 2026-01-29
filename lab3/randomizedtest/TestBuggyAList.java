package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AList<Integer> a = new AList<>();
        BuggyAList<Integer> b = new BuggyAList<>();
        for (int x = 4; x <= 6; x += 1){
            a.addLast(x);
            b.addLast(x);
        }
        assertEquals(a.size(), b.size());
        for (int i = 0; i < a.size(); i += 1){
            assertEquals(a.removeLast(), b.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bsize = B.size();
                assertEquals(size, bsize);
            } else if (operationNumber == 2){
                int size = L.size();
                if (size > 0){
                    int last = L.getLast();
                    int blast = B.getLast();
                    assertEquals(last, blast);
                }
            } else if (operationNumber == 3){
                int size = L.size();
                if (size > 0){
                    int last = L.removeLast();
                    int bLast = B.removeLast();
                    assertEquals(last, bLast);
                }
            }
        }
    }
}
