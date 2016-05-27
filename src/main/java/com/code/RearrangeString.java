package com.code;

import java.util.*;

/**
 * Given a string S, and an integer K, rearrange the string such that similar characters are at least K distance apart.
 * <p/>
 * Example:
 * <p/>
 * S = AAABBBCC, K = 3
 * Result : ABCABCABC (all 'A's are 3 distance apart, similarly with B's and C's)
 * <p/>
 * S = AAABC, K=2 : Not possible. (EDIT : k=3 is not possible).
 * <p/>
 * S = AAADBBCC, K = 2:
 * Result: ABCABCDA
 * <p/>
 * <p/>
 * Created on 5/26/16.
 */
public class RearrangeString {
    class Node implements Comparable<Node>{
        char c;
        int frequency;

        public Node(char c, int f) {
            this.c = c;
            this.frequency = f;
        }

        public int compareTo(Node other) {
            if (this.frequency > other.frequency)
                return -1;
            if (this.frequency < other.frequency)
                return 1;
            if (this.c < other.c)
                return -1;
            if (this.c > other.c)
                return 1;
            return 0;
        }
    }

    public String rearrange(String s, int k) {
        StringBuilder sb = new StringBuilder();
        // calculate frequency
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        // create frequency based heap, if duplicate frequency, order alphabetical
        PriorityQueue<Node> heap = new PriorityQueue<Node>();

        for (Character c : map.keySet()) {
            heap.add(new Node(c, map.get(c)));
        }

        int len = 0;
        // keep removing k elements from heap
        // add them to result string
        // decrease frequency and add back to heap
        while (len < s.length()) { // (n/k) * k log(n) == n log(n)

            int tempK = 0;
            Deque<Node> tempList = new LinkedList<Node>();

            while (tempK < k && !heap.isEmpty()) { // k log(n)
                Node node = heap.remove();
                sb.append(node.c);
                node.frequency--;
                if (node.frequency != 0) {
                    tempList.add(node);
                }
                tempK++;
            }
            while (!tempList.isEmpty()) { //k log(n)
                heap.add(tempList.remove());
            }
            len = len + k;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RearrangeString r = new RearrangeString();
        System.out.println(r.rearrange("aaabbcc", 2));
    }
}
