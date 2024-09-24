public class Decrypt37 {
    public static void main(String[] args) {
        String ciphertext = args[0];
        int[] multKey = stringToVal(removeAs(args[1]));
        int[] addKey = stringToVal(args[2]);
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            plaintext.append(decrypt(ciphertext.charAt(i), i, multKey, addKey));
        }
        System.out.println(plaintext);
    }

    public static char decrypt(char c, int index, int[]multKey, int[]addKey) {
        return deindexer(solveForX(multKey[index % multKey.length], indexer(c), addKey[index % addKey.length]));
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

    public static int solveForX(int n, int y, int m) {
        int yPrime = (y - (n * m) % 37 + 37) % 37; // Ensure y' is positive
        if (gcd(n, 37) != 1) {
            return -1; // No solution
        }
        int nInverse = modInverse(n, 37);
        if (nInverse == -1) {
            return -1; // Inverse does not exist
        }
        return (nInverse * yPrime) % 37;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int modInverse(int n, int mod) {
        for (int x = 1; x < mod; x++) {
            if ((n * x) % mod == 1) {
                return x;
            }
        }
        return -1; // Inverse does not exist
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
