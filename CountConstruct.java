import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 */
public class CountConstruct {


    /**
     * Write a function `countConstruct(target, wordBank)` that accepts a
     * target string and an array of strings.
     * 
     * The function should return the number of ways that the `target` can
     * be constructed by concatenating elements of the `wordBank` array.
     * 
     * You may reuse elements of `wordBank` as many times as needed.
     * 
     * Recursive call without memoization.
     * 
     * m = target.length
     * n = wordBack.length
     * Time: O(n^m * m)  Space: O(m^2)
     */
    static int countConstruct(String target, String[] wordBank) {

        // **** base case(s) ****
        if (target.length() == 0) return 1;

        // **** initialization ****
        int count = 0;

        // **** loop through all the words in the bank ****
        for (String word : wordBank) {

            // **** determine if this word is a prefix ****
            if (target.indexOf(word) == 0) {

                // **** for ease of use ****
                String prefix = word;
            
                // **** generate suffix ****
                String suffix = target.substring(prefix.length());

                // **** make recursive call and update count ****
                count += countConstruct(suffix, wordBank);
            }
        }

        // **** return count ****
        return count;
    }


    /**
     * Write a function `countConstruct(target, wordBank)` that accepts a
     * target string and an array of strings.
     * 
     * The function should return the number of ways that the `target` can
     * be constructed by concatenating elements of the `wordBank` array.
     * 
     * You may reuse elements of `wordBank` as many times as needed.
     * 
     * Entry point for recursive call with memoization.
     * 
     * m = target.length
     * n = wordBack.length
     * Time: O(n * m^2)  Space: O(m^2)
     */
    static int countConstructMemo(String target, String[] wordBank) {

        // **** sanity check(s) ****
        if (target.length() == 0) return 0;

        // **** initiaization ****
        HashMap<String, Integer> memo = new HashMap<>();

        // **** start recursion and get count ****
        int count = countConstructMemo(target, wordBank, memo);

        // ???? ????
        System.out.println("<<< memo: " + memo.toString());

        // **** return count ****
        return count;
    }


    /**
     * Recursive call with memoization.
     */
    static private int countConstructMemo(  String target,
                                            String[] wordBank,
                                            HashMap<String, Integer> memo) {

        // **** base case(s) ****
        if (memo.containsKey(target)) return memo.get(target);
        if (target.length() == 0) return 1;

        // **** initialization ****
        int count = 0;

        // **** loop through all the words in the bank ****
        for (String word : wordBank) {

            // **** determine if this word is a prefix ****
            if (target.indexOf(word) == 0) {

                // **** for ease of use ****
                String prefix = word;
            
                // **** generate suffix ****
                String suffix = target.substring(prefix.length());

                // **** make recursive call and update count ****
                count += countConstructMemo(suffix, wordBank, memo);
            }
        }

        // **** memoize this result ****
        memo.put(target, count);

        // **** return count ****
        return count;
    }


    /**
     * Test scaffold.
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** initialization ****
        long start  = 0;
        long end    = 0;

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read target string ****
        String target = br.readLine().trim();

        // **** read word bank String[] ****
        String[] wordBank = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< target ==>" + target + "<==");
        System.out.println("main <<< wordBank: " + Arrays.toString(wordBank));

        // **** call the function and display result ****
        start = System.currentTimeMillis();
        System.out.println("main <<< countConstruct: " + countConstruct(target, wordBank));
        end = System.currentTimeMillis();
        System.out.println("main <<< time: " + (end - start) + " ms.");

        // **** call the function and display result ****
        start = System.currentTimeMillis();
        System.out.println("main <<< countConstructMemo: " + countConstructMemo(target, wordBank));
        end = System.currentTimeMillis();
        System.out.println("main <<< time: " + (end - start) + " ms.");
    }
}