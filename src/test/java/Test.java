import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        Map<String, String> getenv = System.getenv();

        for (Iterator it = getenv.keySet().iterator(); it.hasNext(); ) {
            String key = (String ) it.next();
            String value = (String ) getenv.get(key);
            System.out.println(key +":" +value);
        }

        Properties properties = System.getProperties();
        Iterator it = properties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.print(entry.getKey() + "=");
            System.out.println(entry.getValue());

        }

        System.out.println(getenv.get("CAD_CONVERTER_HOME"));
        System.out.println(System.getProperty("CAD_CONVERTER_HOME"));
    }
}
