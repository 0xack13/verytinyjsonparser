import java.io.IOException;

public class StringParserInput implements ParserInput {

    private final String jsonStr;
    private final int length;
    private int index;

    // Create default constructor for JSON string
    public StringParserInput(String jsonStr) {
        this.jsonStr = jsonStr;
        this.length = jsonStr.length();
    }
    
    // Implmenent Interface methods
    @Override
    public void close() throws IOException {}

    @Override
    public char read() throws IOException {
        if(index < length) {
            return jsonStr.charAt(index++);
        }
        return Constant.EOF;
    }
    
}
