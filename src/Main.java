import java.lang.invoke.WrongMethodTypeException;
import java.util.Scanner;

public class Main {

    public static int scannerTotalSystemUsers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество пользователей системы: ");
        return scanner.nextInt();
    }

    public static String scannerUserLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите логин: ");
        return scanner.nextLine();
    }

    public static String scannerUserPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите пароль: ");
        return scanner.nextLine();
    }

    public static boolean validateLoginPassword(String currentLogin, String currentPassword) {
        boolean loginMatch = currentLogin.length() <= 20 && currentLogin.matches("[a-zA-Z0-9_]+");
        boolean passwordMatch = currentPassword.length() <= 20 && currentPassword.matches("[a-zA-Z0-9_]+");

        try {
            if (loginMatch && passwordMatch) {
            return true;
            }
        } catch (WrongMethodTypeException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean loginConfirmation(String currentLogin, String currentPassword, User[] users) {

        try {
            for (int i = 0; i < users.length; i++) {
                if (currentLogin.equals(users[i].getLogin()) && currentPassword.equals(users[i].getPassword())) {
                    return true;
                }
            }
        } catch (WrongUserLoginPasswordException e) {
            System.out.println("Повторите попытку входа в систему");
            throw new RuntimeException(e);
        } finally {
            System.out.println("Повторите попытку регистрации в системе");
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println("Регистрация пользователей в системе");

        User[] users = new User[scannerTotalSystemUsers()];
        for (int i = 0; i < users.length; i++) {
            String currentLogin = scannerUserLogin();
            String currentPassword = scannerUserPassword();
            if (validateLoginPassword(currentLogin, currentPassword)) {
                users[i] = new User(currentLogin, currentPassword);
            } else {
                throw new WrongUserLoginPasswordException("Регистрация не возможна. " +
                        "Для регистрации требуется, одновременное соблюдение условий: "
                        + "1) длина строки логина и пароля не должна превышать 20 символов; " +
                        "2) строка может содержать только латинские буквы, цифры и знак подчеркивания;");
            }
        }

        System.out.println("Вход в систему зарегистрированным пользователям");

        String currentLogin = scannerUserLogin();
        String currentPassword = scannerUserPassword();

        if (loginConfirmation(currentLogin, currentPassword, users)) {
            System.out.println("Добро пожаловать! Вы авторизованы в системе");
        } else {
            throw new WrongUserLoginPasswordException("Не верны: логин или пароль. Доступ заблокирован");
        }
    }
}