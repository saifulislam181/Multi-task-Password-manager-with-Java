class Encryption {
    public static String encrypt(String password) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : password.toCharArray()) {
            encrypted.append((char) (ch + 7)); // (x + 5) + 2
        }
        return encrypted.toString();
    }

    public static String decrypt(String encryptedPassword) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : encryptedPassword.toCharArray()) {
            decrypted.append((char) (ch - 7)); // Reverse (x + 5) + 2
        }
        return decrypted.toString();
    }
}
