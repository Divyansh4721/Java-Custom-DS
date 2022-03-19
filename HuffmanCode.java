import java.util.*;

class minPqNode {
    int frequency;
    char data;
    minPqNode left, right;

    minPqNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = null;
        right = null;
    }
}

class HuffmanCodeNode {
    String code;
    Character data;

    HuffmanCodeNode(char data, String code) {
        this.data = data;
        this.code = code;
    }
}

class customComparator implements Comparator<minPqNode> {
    public int compare(minPqNode o1, minPqNode o2) {
        return o1.frequency > o2.frequency ? 1 : -1;
    }
}

class jtemp3 {

    public static HashMap<Character, Integer> frequency(String input) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            hm.put(input.charAt(i), hm.getOrDefault(input.charAt(i), 0) + 1);
        }
        return hm;
    }

    public static void printhm(HashMap<Character, Integer> hm) {
        Set<Character> s = hm.keySet();
        for (Character i : s) {
            System.out.println(i + " " + hm.get(i));
        }
    }

    public static PriorityQueue<minPqNode> fillPQ(HashMap<Character, Integer> hm) {
        Set<Character> s = hm.keySet();
        PriorityQueue<minPqNode> pq = new PriorityQueue<>(new customComparator());
        for (Character i : s) {
            minPqNode mn = new minPqNode(i, hm.get(i));
            pq.add(mn);
        }
        return pq;
    }

    public static void printpq(PriorityQueue<minPqNode> pq) {
        while (!pq.isEmpty()) {
            System.out.println(pq.poll().frequency);
        }
    }

    public static minPqNode fillTree(PriorityQueue<minPqNode> pq) {
        minPqNode parent = null;
        while (!pq.isEmpty()) {
            minPqNode temp1 = pq.poll();
            minPqNode temp2 = pq.poll();
            parent = new minPqNode(' ', temp1.frequency + temp2.frequency);
            parent.left = temp2;
            parent.right = temp1;
            if (pq.isEmpty())
                break;
            pq.add(parent);
        }
        // printhuffman.printTree2D(parent);
        return parent;
    }

    public static void abc(minPqNode root, String code, ArrayList<HuffmanCodeNode> list) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            list.add(new HuffmanCodeNode(root.data, code));
            return;
        }
        abc(root.left, code + "0", list);
        abc(root.right, code + "1", list);
    }

    public static String HuffmanCode(String input) {
        HashMap<Character, Integer> hm = frequency(input);
        // printhm(hm);
        PriorityQueue<minPqNode> pq = fillPQ(hm);
        // printpq(pq);
        minPqNode root = fillTree(pq);
        // printhuffman.printTree2D(root);
        ArrayList<HuffmanCodeNode> list = new ArrayList<>();
        abc(root, "", list);
        String output = "";
        HashMap<Character, String> code = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).data + " " + list.get(i).code);
            code.put(list.get(i).data, list.get(i).code);
        }
        for (int i = 0; i < input.length(); i++) {
            output += code.get(input.charAt(i));
        }
        System.out.println(input);
        System.out.println();
        return output;
    }

    public static void main(String[] args) {
        System.out.println(HuffmanCode(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbccccccccdddddddddddddddgggggggggggggggggggggggggxxxxzz"));
        // int arr[] = { 30, 10, 8, 15, 25, 4, 2 };
        // char input[] = { 'a', 'b', 'c', 'd', 'g', 'x', 'z' };
        // for (int i = 0; i < input.length; i++) {
        // for (int j = 0; j < arr[i]; j++) {
        // System.out.print(input[i]);
        // }
        // }
    }
}

class printhuffman {
    private static void print2darr(int[][] arr, int space) {
        System.out.println("---------------------------------------------");
        space = (space - 1) / 2;
        space = (space - 1) / 2;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < space; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == -10000)
                    System.out.print("  ");
                else if (arr[i][j] == -20000)
                    System.out.print("X ");
                else
                    System.out.print(arr[i][j] + " ");
            }
            System.out.println();
            space = (space - 1) / 2;
        }
        System.out.println("---------------------------------------------");
    }

    private static int treeind = 0;

    private static void fillarr(minPqNode root, int level, int[][] arr, int height, int space) {
        if (level == 1) {
            arr[height][treeind] = (root == null) ? -20000 : root.frequency;
            treeind += 1 + space;
        } else if (root == null && level >= 1) {
            for (int i = 0; i < level; i++)
                treeind += 1 + space;
        }
        if (root == null) {
            return;
        }
        if (level > 1) {
            fillarr(root.left, level - 1, arr, height, space);
            fillarr(root.right, level - 1, arr, height, space);
        }
    }

    private static int heightOfTree(minPqNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }

    public static void printTree2D(minPqNode root) {
        if (root == null)
            return;
        int height = heightOfTree(root);
        int ele = (int) Math.pow(2, height - 1);
        int[][] arr = new int[height][ele + ele - 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = -10000;
            }
        }
        int space = 1;
        for (int i = height - 1; i >= 0; i--) {
            treeind = 0;
            fillarr(root, i + 1, arr, i, space);
            space = (space * 2) + 1;
        }
        print2darr(arr, space);
    }
}