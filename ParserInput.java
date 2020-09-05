import java.io.Closeable;
import java.io.IOException;

public interface ParserInput extends Closeable {
    char read() throws IOException;
}
