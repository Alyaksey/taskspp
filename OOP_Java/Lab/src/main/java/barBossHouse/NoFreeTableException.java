package barBossHouse;

public class NoFreeTableException extends RuntimeException {
    public NoFreeTableException(String message) {
        super(message);
    }
}
