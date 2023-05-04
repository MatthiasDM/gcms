/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdm.gcms.cc;

/**
 *
 * @author Matthias
 */

public class Config {

    public static String packageName = getPackageName();

     public static String getBasePath(String packageName) {
        return System.getProperties().getProperty("user.home") + "/" + packageName;
    }
    
    public static String getPackageName() {
        Class<?> clazz = Config.class;
        Package p = clazz.getPackage();
        return p.getName();
    }
    
    

//    public static boolean checkAPIKey(Map<String, String> parameters) {
//        boolean valid = false;
//        try {
//            String key = parameters.get("key") != null ? parameters.get("key") : "";
//            String command = parameters.get("command") != null ? parameters.get("command") : "";
//            String api = parameters.get("api") != null ? parameters.get("api") : "";
//            return Core.isValidApiKey(api, key, command);
//        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return valid;
//    }
}
