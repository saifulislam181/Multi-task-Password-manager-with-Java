

interface UseCase {
    void login(String email, String password);

    void editPassword(String email);

    void deletePassword(String email);
}
