import java.io.Closeable;
import java.io.IOException;


public class JSONParser implements Closeable {
    private char curr;
    private final ParserInput input;
    private static final int CHAR_BUF_SIZE = 64;
    private char[] cbuf = new char[CHAR_BUF_SIZE];
    private int index;

    // Constructor
    public JSONParser(ParserInput input) {
        this.input = input;
    }

    public JSONType parse() throws IOException {
        JSONType object;
        curr = next();
        switch(curr){
            case Constant.LBRACE:
                next();
                object = parseObject();
                break;
            default:
                throw new JSONParserException("Expected {");
        }
        accept(Constant.EOF);
        return object;
    }

    private JSONObject parseObject() throws IOException {
        JSONObject object = new JSONObject();
        switch(curr) {
            case Constant.QUOTA:
                do {
                    // Get the object's key
                    String key = parseString();
                    accept(Constant.COLON);
                    object.put(key, parseValue());
                } while(nextIfAccept(Constant.COMMA));
                accept(Constant.RBRACE);
                return object;
            case Constant.RBRACE:
                next();
                return object;
            default:
                throw new JSONParserException("Expected }");
        }
    }

    private Object parseValue() throws  IOException {
        switch(curr) {
            case Constant.QUOTA:
                return parseString();
        }
        return parseString();
    }

    private boolean nextIfAccept(char c) throws IOException {
        if(c == curr) {
            next();
            return true;
        }
        return false;
    }

    private String parseString() throws IOException {
        index = 0;
        for(;;) {
            switch(next0()) {
                case Constant.QUOTA:
                    next();
                    return new String(cbuf, 0, index);
                case Constant.EOF:
                default:
                    append(curr);
                    continue;
            }
        }
    }
    
    private void append(char c) {
        cbuf[index++] = c;
    }

    // verifies curr with the input c
    private void accept(char c) throws IOException{
            if(c == curr) {
                next();
            }
    }

    private char next() throws IOException {
        for(;;) {
            switch(next0()) {
                case ' ':
                case '\n':
                case '\t':
                case '\r':
                    continue;
                default:
                    return curr;
            }
        }
    }

    private char next0() throws IOException {
        return curr = input.read();
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub

    }


}
