package ua.ivanyshen.graphswebsite.dao;

public class PasswordEncryptor {
    private static int[] key;

    {
        key = new int[] {21, 6, 7, 11, 12, 18, 9, 3, 1, 19, 16, 22};
    }

    public static String encrypt(String pass) {
        char[] arr = pass.toCharArray();
        char[] newPass = new char[arr.length];
        for(int i=0, j=0; i<arr.length; ++i, ++j) {
            if(j==key.length)
                j=0;
            newPass[i] = (char) (arr[i] + key[j]);
        }
        return String.valueOf(newPass);
    }

    public String decrypt(String pass) {
        char[] arr = pass.toCharArray();
        char[] decryptedPass = new char[arr.length];
        for(int i=0, j=0; i<arr.length; ++i, ++j) {
            if(j==key.length)
                j=0;
            decryptedPass[i] = (char) (arr[i] - key[j]);
        }
        return String.valueOf(decryptedPass);
    }
}
