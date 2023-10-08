public class UserRegistrationImpl {

    public static boolean validateLoginPassword(String currentLogin, String currentPassword, String confirmPassword) {
        boolean loginLength = currentLogin.length() <= 20 && currentLogin.length() >= 5;
        boolean loginMatch = currentLogin.matches("[a-zA-Z0-9_]+");
        boolean passwordLength = currentPassword.length() <= 20 && currentPassword.length() >= 5;
        boolean passwordMatch = currentPassword.matches("[a-zA-Z0-9_]+");
        boolean passwordConfirmed = currentPassword.equals(confirmPassword);

        if (!loginLength && currentLogin.length() < 5) {
            throw new WrongLoginException("Не допустимая длина логина. Длина логина, не менее 5 символов");
        } else if (!loginLength) {
            throw new WrongLoginException("Не допустимая длина логина. Длина логина, не более 20 символов");
        }

        if (!loginMatch) {
            throw new WrongLoginException("Логин может содержать в символы: латинские буквы, цифры и знак подчеркивания");
        }

        if (!passwordLength && currentPassword.length() < 5) {
            throw new WrongPasswordException("Не допустимая длина пароля. Длина пароля, не менее 5 символов");
        } else if (!passwordLength) {
            throw new WrongPasswordException("Не допустимая длина пароля. Длина пароля, не более 20 символов");
        }

        if (!passwordMatch) {
            throw new WrongPasswordException("Пароль может содержать символы: латинские буквы, цифры и знак подчеркивания");
        }

        if (!passwordConfirmed) {
            throw new WrongConfirmPasswordException("Поля: " + "Пароль и " + "Подтверждение пароля, " + "не одинаковые");
        }
        return true;
    }
}

