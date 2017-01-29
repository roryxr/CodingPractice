import java.util.*;

public class Solution {

  public int coinChange(int[] coins, int amount) {
    if (amount <= 0) return 0;
    Arrays.sort(coins);
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int res = minCoins(coins, amount, coins.length - 1, map);
    for (int key : map.keySet()) {
      System.out.println(key + ", " + map.get(key));
    }
    return res;
  }

  /*
  // dfs
  private int minCoins(int[] coins, int remain, int pos) {
    while (pos >= 0 && remain < coins[pos]) pos--;
    if (pos < 0) return -1;
    if (remain == coins[pos]) return 1;
    int nextMinCoins = -1;
    for (int i = pos; i >= 0; i--) {
      int tmp = minCoins(coins, remain - coins[i], i);
      if (tmp != -1) {
        nextMinCoins = nextMinCoins == -1 ? tmp : Math.min(nextMinCoins, tmp);
      }
    }
    return nextMinCoins == -1 ? -1 : 1 + nextMinCoins;
  }
  */

  // dp
  private int minCoins(int[] coins, int remain, int pos, HashMap<Integer, Integer> map) {
    if (map.containsKey(remain)) return map.get(remain);
    while (pos >= 0 && remain < coins[pos]) pos--;
    if (pos < 0) {
      map.put(remain, -1);
      return -1;
    }
    if (remain == coins[pos]) return 1;
    int nextMinCoins = -1;
    for (int i = pos; i >= 0; i--) {
      int tmp = minCoins(coins, remain - coins[i], pos, map);
      if (tmp != -1) {
        nextMinCoins = nextMinCoins == -1 ? tmp : Math.min(nextMinCoins, tmp);
      }
    }
    int res = nextMinCoins == -1 ? -1 : 1 + nextMinCoins;
    map.put(remain, res);
    return res;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] coins = {186, 419, 83, 408};
    int amount = 6249;

    long startTime = System.nanoTime();
    System.out.println("Result: " + s.coinChange(coins, amount));

    long endTime = System.nanoTime();
    float duration = (endTime - startTime) / 1000000f;
    System.out.printf("Execution time: %.4f ms\n", duration);
  }
}
