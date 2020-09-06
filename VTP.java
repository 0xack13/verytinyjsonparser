import java.io.IOException;

public class VTP {
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser(
            new StringParserInput("{\"foo\":\"bar\",\"foo1\":\"bar1\"}")
        );
        var i = parser.parse();
        System.out.print(i);
        JSONPath jp = new JSONPath();
        var pointer = JSONPath.readPath("/foo1", i);
        System.out.print(pointer);
    }
}
    