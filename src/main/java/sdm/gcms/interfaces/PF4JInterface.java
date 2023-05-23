/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sdm.gcms.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author Matthias
 */
public interface PF4JInterface {
    public abstract String getPlugin(String pluginId);
    public abstract String reloadPlugin(String pluginId);
    public abstract String pluginsInfo() throws JsonProcessingException ;
}
