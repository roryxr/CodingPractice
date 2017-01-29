import java.util.HashMap;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.minWindow("abcdefallll", "aal"));
  }

  public String minWindow(String s, String t) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    if (s == null || t == null || s.length() < t.length()) return "";
    int nUnique = 0;
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        nUnique++;
        map.put(c, 1);
      }
    }
    // First cover range
    int pos = 0;
    int record = 0;
    while (pos < s.length() && record < nUnique) {
      char c = s.charAt(pos);
      if (map.containsKey(c)) {
        int curr = map.get(c);
        if (curr == 1) {
          record++;
        }
        map.put(c, curr - 1);
      }
      pos++;
    }

    if (record < nUnique) {
      // Could not find a window.
      return "";
    }
    // Move and find the minimum window;
    int start = 0;
    int minStart = 0, minEnd = pos;

    while (pos < s.length()) {
      start = getNextStart(s, map, start);
      if (pos - start < minEnd - minStart) {
        minEnd = pos;
        minStart = start;
      }
      while (pos < s.length() && !map.containsKey(s.charAt(pos))) {
        pos++;
      }
      if (pos != s.length()) {
        map.put(s.charAt(pos), map.get(s.charAt(pos)) - 1);
        pos++;
      }
    }

    start = getNextStart(s, map, start);
    if (pos - start < minEnd - minStart) {
      minEnd = pos;
      minStart = start;
    }
    return s.substring(minStart, minEnd);
  }

  private int getNextStart(String s, HashMap<Character, Integer> map, int start) {
    while (true) {
      char c = s.charAt(start);
      if (!map.containsKey(c)) {
        start++;
      } else {
        if (map.get(c) == 0) {
          break;
        } else {
          map.put(c, map.get(c) + 1);
          start++;
        }
      }
    }
    return start;
  }
}
