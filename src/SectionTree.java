import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SectionTree {

    public static Type[] list;

    public static void main(String[] args) {
        List<Type> list1 = new ArrayList<>() {{
            add(new Type(1, 1));
            add(new Type(1, 1));
            add(new Type(3, 1));
            add(new Type(2, 1));
            add(new Type(3, 1));
            add(new Type(4, 1));
            add(new Type(3, 1));
        }};
        int[] arr = new int[]{1,1,3,2,3,4,3};
        list = new Type[(int) Math.pow(2, (int) Math.sqrt(list1.size() * 2) + 1)];
        buildTree(list1, 0, 0, list1.size());
        System.out.println(Arrays.toString(list));
        System.out.println(sum(3, 7, 1, 0, list1.size()));
        System.out.println(Arrays.toString(list));
       // Smaller smaller = new Smaller(arr);
    }

    public static void buildTree(List<Type> l, int v, int left, int right) {
        if (left == right - 1) {
            list[v] = new Type(l.get(left).first, 1);
        } else {
            int tm = (left + right) / 2;
            buildTree(l, v * 2 + 1, left, tm);
            buildTree(l, v * 2 + 2, tm, right);
            //list[v] = list[v * 2 + 1] + list[v * 2 + 2];
            list[v] = combine(list[v * 2 + 1] , list[v * 2 + 2]);
        }
    }

    public static Type sum(int l, int r, int x, int xl, int xr) {
        if (r <= xl || l >= xr) {
            return new Type(Integer.MAX_VALUE, 0);
        }
        if (xl >= l && xr <= r) {
            return list[x];
        }
        int xm = (xl + xr) / 2;
        Type left = sum(l, r, 2 * x + 1, xl, xm);
        Type right = sum(l, r, 2 * x + 2, xm, xr);
        return combine(left, right);
    }

    public static Type combine(Type a, Type b) {
        if (a.first < b.first) return a;
        if (b.first < a.first) return b;
        return new Type(a.first, a.second + b.second);
    }

    static class Type {
        int first;
        int second;

        public Type(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public String toString(){
            return "number=" + first + " - " + "count=" + second;
        }
    }


//    public static void changeElement(int i, int v, int x, int xl, int xr) {
//        if (xl == xr - 1) {
//            list[x] = v;
//            return;
//        }
//        int xm = (xl + xr) / 2;
//        if (i < xm) {
//            changeElement(i, v, 2 * x + 1, xl, xm);
//        } else {
//            changeElement(i, v, 2 * x + 2, xm, xr);
//        }
//        list[x] = list[2 * x + 1] + list[2 * x + 2];
//    }

}
class Smaller {

    private static int[] arr;

    private static int[] tree;

    public Smaller(int[] arr) {
        this.arr = arr;
        smaller(arr);
    }

    public static int[] smaller(int[] nums) {
        tree = new int[40002];
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = query(0, nums[i] - 1);
            add(nums[i]);
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void add (int num) {
        num += 3001;
        tree[num] += 1;
        for (int i = num / 2; i != 0; i /= 2) {
            tree[i] += 1;
        }
    }

    public static int query (int left, int right) {
        left += 3001;
        right += 3001;

        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left];
                left += 1;
            }
            if (right % 2 == 0) {
                sum += tree[right];
                right -= 1;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }
}