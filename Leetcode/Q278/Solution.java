public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    s.firstBadVersion2(2126753390);
    System.out.println("Separator");
    s.firstBadVersion(2126753390);
  }

  public int firstBadVersion(int n) {
    int lb = 1, ub = n;
    while (lb < ub) {
      int mid = (lb + ub) / 2;
      System.out.println(mid);
      if (!isBadVersion(mid)) {
        lb = mid + 1;
      } else {
        ub = mid;
      }
    }
    return lb;
  }

  public int firstBadVersion2(int n) {
    int lb = 1, ub = n;
    while (lb < ub) {
      int mid = lb + (ub - lb) / 2;
      System.out.println(mid);
      if (!isBadVersion(mid)) {
        lb = mid + 1;
      } else {
        ub = mid;
      }
    }
    return lb;
  }

  private boolean isBadVersion(int v) {
    if (v >= 1702766719) return true;
    return false;
  }
}
