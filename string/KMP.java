class KMP {
    String p;
    int np;
    //the maximumlength of p's suffix (ending at the corresponding position) that matches p's prefix
    int[] pi;
    KMP(String p) {
        this.p = p;
        np = p.length();
        pi = new int[np];
        int len = 0;
        for (int i = 0; i < np; i++) {
            while (len != 0 && p.charAt(i) != p.charAt(len))
                len = pi[len - 1];
            if (len < i && p.charAt(i) == p.charAt(len))
                len++;
            pi[i] = len;
        }
    }
    //return a list of starting indexes when matching
    ArrayList<Integer> find(String s) {
        ArrayList<Integer> a = new ArrayList<>();
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (len == np)
                len = pi[len - 1];
            while (len != 0 && s.charAt(i) != p.charAt(len))
                len = pi[len - 1];
            if (s.charAt(i) == p.charAt(len))
                len++;
            if (len == np) //hit
                a.add(i - np + 1);
        }
        return a;
    }
}
