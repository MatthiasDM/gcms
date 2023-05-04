/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.cc.collections;

/**
 *
 * @author Matthias
 */
public class PageAccess {

    String type;
    /**
     * This field specifies a regular expression used to include or exclude domain specific access;
     */
    String accessScope;
 
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessScope() {
        return accessScope;
    }

    public void setAccessScope(String accessScope) {
        this.accessScope = accessScope;
    }

}
