package com.urwoo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Scanner;

/**
 * Unit test for simple WSiteManagerApp.
 */
public class AppTest {

    // B的位置-他前面G出现的次数
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        int b = 0;
        int g = 0;
        int bsum = 0;
        int gsum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'G') {
                gsum += (i - g);
                g++;
            } else if (s.charAt(i) == 'B') {
                bsum += (i - b);
                b++;
            }
        }
        System.err.println(bsum  + "," + gsum);
        System.out.println(Math.min(bsum, gsum));
    }
}