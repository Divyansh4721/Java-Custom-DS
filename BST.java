class BinarySearchTree {
    BinaryTreeNode<Integer> root = null;

    private BinaryTreeNode<Integer> inserthelper(BinaryTreeNode<Integer> temp, int data) {
        if (temp == null)
            return new BinaryTreeNode<>(data);
        if (temp.data >= data)
            temp.left = inserthelper(temp.left, data);
        else if (temp.data < data)
            temp.right = inserthelper(temp.right, data);
        return temp;
    }

    public void insert(int data) {
        root = inserthelper(root, data);
    }

    private BinaryTreeNode<Integer> removeHelper(BinaryTreeNode<Integer> temp, int data) {
        if (temp == null)
            return null;
        if (temp.data > data) {
            temp.left = removeHelper(temp.left, data);
            return temp;
        } else if (temp.data < data) {
            temp.right = removeHelper(temp.right, data);
            return temp;
        } else {
            if (temp.left == null && temp.right == null) {
                return null;
            } else if (temp.left == null) {
                return temp.right;
            } else if (temp.right == null) {
                return temp.left;
            } else {
                BinaryTreeNode<Integer> tail = temp.right;
                while (tail.left != null)
                    tail = tail.left;
                tail.left = temp.left;
                temp = temp.right;
                return temp;
            }
        }
    }

    public void remove(int data) {
        root = removeHelper(root, data);
    }

    private void printTreeHelper(BinaryTreeNode<Integer> temp) {
        if (temp == null)
            return;

        System.out.print(temp.data + ":");
        if (temp.left != null)
            System.out.print("L:" + temp.left.data + ",");
        if (temp.right != null)
            System.out.print("R:" + temp.right.data);
        System.out.println();

        if (temp.left != null)
            printTreeHelper(temp.left);
        // System.out.println(temp.data);
        if (temp.right != null)
            printTreeHelper(temp.right);
    }

    public void printTree() {
        printTreeHelper(root);
    }

    private boolean searchHelper(BinaryTreeNode<Integer> temp, int data) {
        if (temp == null)
            return false;
        if (temp.data == data)
            return true;
        if (temp.data > data)
            return searchHelper(temp.left, data);
        return searchHelper(temp.right, data);
    }

    public boolean search(int data) {
        return searchHelper(root, data);
    }

}