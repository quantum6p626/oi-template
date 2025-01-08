class Trie {
    class Node {
        boolean end;
        Node[] c;
        Node() {
            end = false;
            c = new Node[26];
        }
    }
    Node root;
    public Trie() {
        root = new Node();
    }
    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.c[ch - 'a'] == null)
                node.c[ch - 'a'] = new Node();
            node = node.c[ch - 'a'];
        }
        node.end = true;
    }
    public boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.c[ch - 'a'] == null)
                return false;
            node = node.c[ch - 'a'];
        }
        return node.end;
    }
    public boolean startsWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (node.c[ch - 'a'] == null)
                return false;
            node = node.c[ch - 'a'];
        }
        return true;
    }
}
