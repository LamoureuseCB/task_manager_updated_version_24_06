package service_parts;

import java.io.IOException;

public class ManagerLoadException extends IOException {
    public ManagerLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
