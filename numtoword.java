import java.util.HashMap;

public class numtoword {
    public static String twodigit(int n, HashMap<Integer, String> map) {
        if (n % 100 == 0)
            return "";
        if (n % 100 < 20 || n % 10 == 0)
            return map.get(n % 100);
        else
            return map.get(((n % 100) / 10) * 10) + " " + map.get(n % 10);
    }

    public static String convert(int n) {
        int num[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 30, 40, 50, 60, 70, 80,
                90, 100, 1000, 100000, 10000000 };
        String word[] = { "One", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Ninteen",
                "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty",
                "Ninety", "Hundred", "Thousand",
                "Lakh", "Crore"
        };
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            map.put(num[i], word[i]);
        }
        int count = 0;
        String ans = "";
        while (n > 0) {
            int unit = n % 10;
            if (count == 0) {
                ans = twodigit(n, map);
                n /= 10;
                count++;
            } else if (count == 2 && unit != 0) {
                ans = map.get(unit) + " " + map.get(100) + " " + ans;
            } else if (count > 2) {
                if (twodigit(n, map) != "")
                    ans = twodigit(n, map) + " " + map.get((int) Math.pow(10, count)) + " " + ans;
                n /= 10;
                count++;
            }
            n /= 10;
            count++;
        }
        return ans;

    }

    public static void main(String[] args) {
        int n = 1;
        for (int i = 0; i < 9; i++) {
            System.out.println(convert(n));
            n *= 10;
        }
        System.out.println(convert(27001354));
    }
}
