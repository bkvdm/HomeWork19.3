public class WrongConfirmPasswordException extends RuntimeException {
    public WrongConfirmPasswordException(String massage) {
        super(massage);
    }
}
