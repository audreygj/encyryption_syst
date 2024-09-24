public class Encrypt37 {
    public static void main(String[] args) {
        String plaintext = args[0];
        int[] multKey = stringToVal(removeAs(args[1]));
        int[] addkey = stringToVal(args[2]);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            ciphertext.append(encrypt(plaintext.charAt(i), i, multKey, addkey));
        }
        System.out.println(ciphertext);
    }
    public static char encrypt(char c, int index, int[] multKey, int[] addKey) {
        int val = indexer(c);
        val += addKey[index % addKey.length];
        val *= multKey[index % multKey.length];
        return deindexer((val % 37));
    }
    public static int[] stringToVal(String s) {
        int[] vals = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            vals[i] = indexer(s.charAt(i));
        }
        return vals;
    }
    public static int indexer(char c) {
        if ((c > 47) && (c < 58)) {
            return (c - 22);
        } else if ((c > 64) && (c < 91)) {
            return (c - 65);
        } else if (c == 95) {
            return 36;
        } else {
            return -1;
        }
    }
    public static char deindexer(int i) {
        if ((i > 25) && (i < 36)) {
            return ((char) (i + 22));
        } else if ((i > -1) && (i < 26)) {
            return ((char) (i + 65));
        } else if (i == 36) {
            return ((char) 95);
        } else {
            return ' ';
        }
    }

    public static String removeAs(String key) {
        StringBuilder str = new StringBuilder(key);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                str.deleteCharAt(i);
            }
        }
        return str.toString();
    }
}
