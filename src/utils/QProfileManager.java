package utils;
import java.util.HashMap;
/**
 * @Author Unsal Ozturk
 * @Version 20190501
 * Static profile manager class
 * Allows concurrent access through a monitor
 */
public class QProfileManager {

    private static final int NO_PROFILES = 6;
    private static HashMap<Integer, QProfile> profiles;

    public static synchronized QProfile getProfile(int ID) {
        return profiles.get(ID);
    }

    public static synchronized void persistProfiles() {
        System.out.println("Persisting...");
        for(int i = 0; i < NO_PROFILES; i++) {
            QProfile profile = profiles.get(i);
            if(profile != null) {
                profile.encode();
            }
        }
    }

    public static synchronized void init() {
        profiles = new HashMap<>();
        for(int i = 0; i < NO_PROFILES; i++) {
            profiles.put(i,null);
            QProfile profile = new QProfile(i);
            if(profile.isValid()) {
                profiles.put(i, profile);
            }
        }
    }

    public static synchronized boolean delete(int ID) {
        QProfile profile = profiles.get(ID);
        if(profile != null) {
            profiles.put(ID, null);
            return profile.delete();
        } else {
            return false;
        }
    }

    public static void put(int ID, QProfile profile) {
        profiles.put(ID, profile);
    }
}
