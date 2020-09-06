import java.util.ArrayList;
import java.util.List;

public class JSONPath {
    public static <T> T readPath(String path, JSONType json) {
        if(path!=null && path.length()==0) {
            return (T) json;
        }
        return readPath(path, parse(path), json, 0);
    }

    private static <T> T readPath(String raw, List<String> path, Object json, int index) {
        int len = path.size();
        if (index == len) {
            return (T) json;
        }
        if (index > len) {
            throw new JSONPathException("unmatched path ['" + raw + "']");
        }
        if (json == null || !(json instanceof JSONType)) {
            throw new JSONPathException("unmatched path ['" + raw + "']");
        }
        String p = path.get(index); 
        if (json instanceof JSONObject) {
            JSONObject obj = (JSONObject) json;
            if (!obj.containsKey(p)) {
                throw new JSONPathException("not found path ['" + raw + "'] at ['" + p + "']");
            }
            return readPath(raw, path, obj.get(p), ++index);
        } else {
            throw new JSONPathException("unmatched path ['" + raw + "'] at ['" + p + "']");
        }
    }

    private static List<String> parse(String path) {
        if (path == null || path.charAt(0) != '/') {
            throw new JSONPathException("invalid path ['" + path + "']");
        }
        char[] ary = path.toCharArray();
        List<String> r = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        char prev = '/';
        for (int i = 1; i < ary.length; i++) {
            char c = ary[i];
            switch (c) {
                case '~':
                    break;
                case '/':
                    if (prev == '~') {
                        builder.append('~');
                    }
                    r.add(builder.toString());
                    builder.setLength(0);
                    break;
                case '0':
                    if (prev == '~') {
                        builder.append("~");
                    } else {
                        builder.append(c);
                    }
                    break;
                case '1':
                    if (prev == '~') {
                        builder.append("/");
                    } else {
                        builder.append(c);
                    }
                    break;
                default:
                    if (prev == '~') {
                        builder.append('~');
                    }
                    builder.append(c);
                    break;
            }
            prev = c;
        }
        if (prev == '~') {
            builder.append("~");
        }
        r.add(builder.toString());
        return r;
    }
}
