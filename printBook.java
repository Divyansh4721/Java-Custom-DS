import java.util.ArrayList;

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class printBook {
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

    private static void fillarr(BinaryTreeNode<Integer> root, int level, int[][] arr, int height, int space) {
        if (level == 1) {
            // print2darr(arr);
            arr[height][treeind] = (root == null) ? -20000 : root.data;
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

    private static int heightOfTree(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }

    public static void printTree2D(BinaryTreeNode<Integer> root) {
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

    private static void fillarrheap(ArrayList<Integer> input, int root, int level, int[][] arr, int height, int space) {
        if (level == 1) {
            // print2darr(arr);
            arr[height][treeind] = (input.size() <= root) ? -20000 : input.get(root);
            treeind += 1 + space;
        } else if (input.size() <= root && level >= 1) {
            for (int i = 0; i < level; i++)
                treeind += 1 + space;
        }
        if (input.size() <= root) {
            return;
        }
        if (level > 1) {
            fillarrheap(input, (2 * root) + 1, level - 1, arr, height, space);
            fillarrheap(input, (2 * root) + 2, level - 1, arr, height, space);
        }
    }

    private static int heightOfHeap(ArrayList<Integer> input, int root) {
        if (input.size() <= root) {
            return 0;
        }
        return 1 + Math.max(heightOfHeap(input, (2 * root) + 1), heightOfHeap(input, (2 * root) + 2));
    }

    public static void printHeapArr(int arr[]) {
        ArrayList<Integer> inp = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            inp.add(arr[i]);
        }
        printBook.printHeapList(inp, 0);
    }

    public static void printHeapList(ArrayList<Integer> input, int root) {
        if (input.size() <= root) {
            System.out.println("---------------------------------------------");
            System.out.println();
            System.out.println("---------------------------------------------");
            return;
        }
        int height = heightOfHeap(input, root);
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
            fillarrheap(input, root, i + 1, arr, i, space);
            space = (space * 2) + 1;
        }
        print2darr(arr, space);
    }
}
