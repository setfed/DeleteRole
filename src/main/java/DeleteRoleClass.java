import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DeleteRoleClass {
    public static Map<String, String> deleteRole(Map<String, String> oldRules, String role) {
        Map<String, String> newRules = new HashMap<>();

        for (Map.Entry<String, String> entry : oldRules.entrySet()) {
            String key = entry.getKey();
            String value = Arrays.stream(entry.getValue()
                                              .split(","))
                                 .sorted()
                                 .filter(x -> !x.equals(role))
                                 .collect(Collectors.joining(","));

            newRules.put(key, value);
        }
        return newRules;
    }
}
