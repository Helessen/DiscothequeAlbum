package Exception;

public class AlbumDejaExistantException extends RuntimeException {
    public AlbumDejaExistantException(String message) {
        super(message);
    }
}
