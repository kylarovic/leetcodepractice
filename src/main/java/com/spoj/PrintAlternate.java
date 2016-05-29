package com.spoj;

import java.util.Scanner;

/**
 * Created on 5/27/16.
 */
public class PrintAlternate {

    public void printAlternate() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        for (int i = 0; i < size; i++) {
            char[] str = in.hasNext() ? in.next().toCharArray() : new char[0];
            int k = 0;
            String temp = "";
            while (k <= (str.length - 1) / 2) {
                temp = temp + str[k];
                k = k + 2;
            }
            System.out.println(temp);
        }
    }

    public void printChessBoard() {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();

        for (int i = 0; i < size; i++) {
            char even = '.', odd = '*';
            int m = in.nextInt();
            int n = in.nextInt();
            for (int j = 0; j < m; j++) {
                char tempSwap = even;
                even = odd;
                odd = tempSwap;
                String temp = "";
                for (int k = 0; k < n; k++) {
                    if (k % 2 == 0) {
                        temp = temp + even;
                    } else {
                        temp = temp + odd;
                    }
                }
                System.out.println(temp);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        PrintAlternate p = new PrintAlternate();
        p.printChessBoard();
    }
}
