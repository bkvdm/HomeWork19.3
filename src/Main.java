import java.util.Scanner;

public class Main {

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

    public static String scannerUserConfirmPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Подтвердите пароль: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String currentLogin = scannerUserLogin();
        String currentPassword = scannerUserPassword();
        String confirmPassword = scannerUserConfirmPassword();

        User userRegistered = new User(currentLogin, currentPassword);

        try {
            boolean validateLoginPassword = UserRegistrationImpl.validateLoginPassword(userRegistered.getLogin(), userRegistered.getPassword(), confirmPassword);
            if (validateLoginPassword) {
                System.out.println("Регистрация прошла успешно");
            }
        } catch (WrongLoginException e) {
            System.out.println("Логин не верен, регистрация не возможна");
            System.out.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println("Пароль не верен, регистрация не возможна");
            System.out.println(e.getMessage());
        } catch (WrongConfirmPasswordException e) {
            System.out.println("Пароль не подтверждён, регистрация не возможна");
            System.out.println(e.getMessage());
        }
    }
}
