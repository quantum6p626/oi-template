class KMP {
    String p;
    int np;
    int[] pi;
    void init(String p) {
        this.p = p;
        np = p.length();
        pi = new int[np];
        int pos = -1;
        for (int i = 0; i < np; i++) {
            while (pos != -1 && p.charAt(i) != p.charAt(pos + 1))
                pos = pi[pos];
            if (pos + 1 < i && p.charAt(i) == p.charAt(pos + 1))
                pos++;
            pi[i] = pos;
        }
    }
    int find(String s) {
        int pos = -1;
        for (int i = 0; i < s.length(); i++) {
            if (pos == np - 1)
                pos = pi[pos];
            while (pos != -1 && s.charAt(i) != p.charAt(pos + 1))
                pos = pi[pos];
            if (s.charAt(i) == p.charAt(pos + 1))
                pos++;
            if (pos == np - 1) //hit
                return i - np + 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        String text = "abcdabcdeabc", pattern = "abcde";
        KMP k = new KMP();
        k.init(pattern);
        int firstPos = k.find(text);
        System.out.printf("text = %s, pattern = %s, firstPos = %d\n", text, pattern, firstPos);
    }
}
