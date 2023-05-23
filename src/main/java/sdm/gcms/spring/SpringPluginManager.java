/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.spring;

import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFactory;
import org.pf4j.spring.SpringExtensionFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Matthias
 */
public class SpringPluginManager extends DefaultPluginManager {

    private ApplicationContext applicationContext;

    @Override
    protected ExtensionFactory createExtensionFactory() {        
        return new SpringExtensionFactory(this);
    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
