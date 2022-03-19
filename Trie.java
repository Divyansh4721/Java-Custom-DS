import java.util.*;

class TrieNode {
    char data;
    boolean isTerminating;
    TrieNode children[];
    int childCount;

    public TrieNode(char data) {
        this.data = data;
        isTerminating = false;
        children = new TrieNode[26];
        childCount = 0;
    }
}

class Trie {
    private TrieNode root;
    public int count;

    public Trie() {
        root = new TrieNode('\0');
    }

    private int returnIndex(char data) {
        return data - 'A';
    }

    public int size() {
        return root.childCount;
    }

    public void printTrie(TrieNode root) {
        if (root.childCount == 0) {
            System.out.println();
            return;
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                System.out.print("(" + root.children[i].data + " " + root.children[i].isTerminating + ")");
                printTrie(root.children[i]);
            }
        }
    }

    public void printTrie() {
        printTrie(root);
        System.out.println();
    }

    private void add(String input, TrieNode root) {
        if (input.length() == 0) {
            root.isTerminating = true;
            return;
        }
        char data = input.charAt(0);
        int index = returnIndex(data);
        if (root.children[index] == null) {
            root.children[index] = new TrieNode(data);
        }
        root.childCount++;
        add(input.substring(1), root.children[index]);
    }

    public void add(String input) {
        add(input.toUpperCase(), root);
    }

    private boolean search(String input, TrieNode root) {
        if (input.length() == 0) {
            return root.isTerminating;
        }
        char data = input.charAt(0);
        int index = returnIndex(data);
        if (root.children[index] == null) {
            return false;
        }
        return search(input.substring(1), root.children[index]);
    }

    public boolean search(String input) {
        return search(input.toUpperCase(), root);
    }

    private void remove(String input, TrieNode root) {
        if (input.length() == 0) {
            root.isTerminating = false;
            return;
        }
        char data = input.charAt(0);
        int index = returnIndex(data);
        if (root.children[index] == null) {
            return;
        }
        root.childCount--;
        remove(input.substring(1), root.children[index]);
        if (root.childCount == 0) {
            root.children[index] = null;
        }
    }

    public void remove(String input) {
        remove(input.toUpperCase(), root);
    }

    private void printPattern(TrieNode root, String pattern) {
        if (root.isTerminating) {
            System.out.println((pattern + root.data).toLowerCase());
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                printPattern(root.children[i], pattern + root.data);
            }
        }
    }

    private TrieNode findNode(TrieNode root, String input) {
        if (input.length() == 0) {
            return root;
        }
        char data = input.charAt(0);
        int index = returnIndex(data);
        if (root.children[index] == null) {
            return null;
        }
        return findNode(root.children[index], input.substring(1));
    }

    public void autoComplete(ArrayList<String> input, String word) {
        for (int i = 0; i < input.size(); i++) {
            add(input.get(i));
        }
        word = word.toUpperCase();
        TrieNode temp = findNode(root, word);
        if (temp == null) {
            return;
        }
        printPattern(temp, word.substring(0, word.length() - 1));
    }

}

class temp {
    public static void main(String[] args) {
        Trie t = new Trie();
        int n = 7;
        ArrayList<String> input = new ArrayList<String>();
        String[] words = { "do", "dont", "no", "not", "note", "notes", "den" };
        for (int i = 0; i < n; i++) {
            input.add(words[i]);
        }
        String pattern = "no";
        t.autoComplete(input, pattern);
    }
}