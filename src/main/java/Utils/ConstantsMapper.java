package Utils;


import java.util.HashMap;
import java.util.Map;

public class ConstantsMapper {

    private static final Map<String, String> constants = new HashMap<>();

    static {
        constants.put("ADMIN_TAB", AppConstants.ADMIN);
        constants.put("PIM_TAB", AppConstants.PIM);
        constants.put("ROLE_ADMIN", AppConstants.ROLE_ADMIN);
        constants.put("ROLE_ESS", AppConstants.ROLE_ESS);
        constants.put("STATUS_ENABLED", AppConstants.STATUS_ENABLED);
        constants.put("STATUS_DISABLED", AppConstants.STATUS_DISABLED);
    }


    public static String resolve(String key) {
        String value = constants.get(key);
//        System.out.println("Resolving: " + key + " → " + value);
        return value != null ? value : key;
    }

}

