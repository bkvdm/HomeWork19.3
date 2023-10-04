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

    public static boolean validateLoginPassword(String loginOrPassword) {
        try {
            if (loginOrPassword.length() > 20) {
                throw new WrongMethodTypeException("Длина строки не должна превышать 20 символов");
            }
            if (!loginOrPassword.matches("[a-zA-Z0-9_]+")) {
                throw new WrongMethodTypeException("Строка может содержать только латинские буквы, цифры и знак подчеркивания");
            }
            return true;
        } catch (WrongMethodTypeException e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
            return false;
        }
    }

    public static boolean loginConfirmation(String[][] users, String userLogin, String userPassword) {
        boolean userVerified = false;
        try {
            for (int i = 0; i < users.length; i++) {
                if (userLogin.equals(users[i][0]) && userPassword.equals(users[i][1])) {
                    userVerified = true;
                } else {
                    if (i == users.length - 1) {
                        throw new WrongMethodTypeException("Пользователь с таким логином и паролем не найден в системе. Или не верны имя пользователя или пароль. Повторите попытку");
                    }
                }
            }
        } catch (WrongMethodTypeException e) {
            System.out.println("Ошибка авторизации: " + e.getMessage());
        }
        return userVerified;
    }

    public static void main(String[] args) {
        int totalSystemUsers = scannerTotalSystemUsers();
        String[][] users = new String[totalSystemUsers][2];
        String userLogin = null;
        String userPassword = null;
        for (int i = 0; i < users.length; i++) {
            userLogin = scannerUserLogin();
            if (validateLoginPassword(userLogin)) {
                users[i][0] = userLogin;
            } else break;
            userPassword = scannerUserPassword();
            if (validateLoginPassword(userPassword)) {
                users[i][1] = userPassword;
            } else break;
            if (i < users.length && i != users.length - 1) {
                System.out.println("Зарегистрируйте следующего пользователя");
            } else if (i == users.length - 1) {
                System.out.println("Все пользователи из " + users.length + " возможных, зарегистрированы");
            }
        }
        while (loginConfirmation(users, userLogin, userPassword) || !loginConfirmation(users, userLogin, userPassword)) {
            System.out.println("Авторизация в системе");
            userLogin = scannerUserLogin();
            userPassword = scannerUserPassword();
            if (loginConfirmation(users, userLogin, userPassword)) {
                System.out.println("Добро пожаловать " + userLogin + "! Вы авторизованы в системе ");
            }
        }
    }
}