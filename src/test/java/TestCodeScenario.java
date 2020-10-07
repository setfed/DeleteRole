import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestCodeScenario {
    @Test
    public void shouldCorrectDeleteRestRule() {
        // Assert
        Map<String, String> oldRules = new HashMap<String, String>() {{
            put("/**:POST,DELETE,PUT", "ROLE_ADMIN,admin_1,admin_2,admin_4,admin_37,admin_38,admin_39,admin_40");
            put("/**:GET", "ROLE_ADMIN,admin_1,admin_2,admin_4,admin_37,admin_38,admin_39,admin_40");
        }};

        // Act
        Map<String, String> result1 = DeleteRoleClass.deleteRole(oldRules, "admin_4");
        Map<String, String> result2 = DeleteRoleClass.deleteRole(result1, "admin_40");
        Map<String, String> result3 = DeleteRoleClass.deleteRole(result2, "not_exist_role");

        // Assets
        assertEquals("ROLE_ADMIN,admin_1,admin_2,admin_37,admin_38,admin_39,admin_40", result1.get("/**:GET"));
        assertEquals("ROLE_ADMIN,admin_1,admin_2,admin_37,admin_38,admin_39", result2.get("/**:GET"));
        assertEquals("ROLE_ADMIN,admin_1,admin_2,admin_37,admin_38,admin_39", result2.get("/**:POST,DELETE,PUT"));
        assertEquals("ROLE_ADMIN,admin_1,admin_2,admin_37,admin_38,admin_39", result3.get("/**:POST,DELETE,PUT"));
    }
}
