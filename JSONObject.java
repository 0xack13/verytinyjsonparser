import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JSONObject implements JSONType, Map<String, Object> {

    private final Map<String, Object> map;

    public JSONObject() {
        this.map = new HashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        // return map.values().toString();
        if (map == null || map.size() == 0) {
            return "{}";
        }
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if (value == null) {
                builder.append("null");
            } else {
                builder.append(value);
            }
            if (it.hasNext()) {
                builder.append(',');
            }
        }
        builder.append('}');
        return builder.toString();
    }
    
}
