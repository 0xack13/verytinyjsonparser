import java.io.IOException;

public class VTP {
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser(
            new StringParserInput("{\"foo\":\"bar\"}")
        );
        var i = parser.parse();
        System.out.print(i);
    }
}
    