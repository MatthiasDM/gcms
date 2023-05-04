/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.cc.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import org.pf4j.ExtensionFactory;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import sdm.gcms.cc.Config;
import sdm.gcms.cc.interfaces.PF4JInterface;
import sdm.gcms.shared.database.Core;

/**
 *
 * @author Matthias
 */
@Service("pf4j")
public class SpringPluginService implements PF4JInterface {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AbstractAutowireCapableBeanFactory beanFactory;

    private static final Logger log = LoggerFactory.getLogger(SpringPluginService.class);

    List<PluginWrapper> startedPlugins;
    SpringPluginManager pluginManager;

    @PostConstruct
    public void init() {
        initExtensionInjector();
    }

    public void initExtensionInjector() {
        Path p = Paths.get(Config.getBasePath(Config.packageName) + "/plugins");
        pluginManager = new SpringPluginManager(p);
        pluginManager.loadPlugins();
        pluginManager.startPlugins();
        pluginManager.setApplicationContext(applicationContext);
        ExtensionFactory extensionFactory = pluginManager.getExtensionFactory();

        // add extensions from classpath (non plugin)
        Set<String> extensionClassNames = pluginManager.getExtensionClassNames(null);
        for (String extensionClassName : extensionClassNames) {
            try {
                log.info("Register extension '{}' as bean", extensionClassName);
                Class<?> extensionClass = getClass().getClassLoader().loadClass(extensionClassName);
                beanFactory.registerSingleton(extensionClassName, extensionFactory.create(extensionClass));
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }

        // add extensions for each started plugin
        startedPlugins = pluginManager.getStartedPlugins();
        for (PluginWrapper plugin : startedPlugins) {
            log.info("Registering extensions of the plugin '{}' as beans", plugin.getPluginId());
            extensionClassNames = pluginManager.getExtensionClassNames(plugin.getPluginId());
            for (String extensionClassName : extensionClassNames) {
                try {
                    log.info("Register extension '{}' as bean", extensionClassName);
                    Class<?> extensionClass = plugin.getPluginClassLoader().loadClass(extensionClassName);
                    beanFactory.registerSingleton(extensionClassName, extensionFactory.create(extensionClass));
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public String getPlugin(String pluginId) {
        Optional<PluginWrapper> pluginOptional = startedPlugins.stream().filter(p -> p.getPluginId() == pluginId).findFirst();
        if (pluginOptional.isPresent()) {
            return pluginOptional.get().getPluginId();
        } else {
            return "No plugin found";
        }
    }

    @Override
    public String reloadPlugin(String pluginId) {
        pluginManager.init();
        return "reloaded plugin " + pluginId;
    }

    @Override
    public String pluginsInfo() throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        for(PluginWrapper pluginWrapper: pluginManager.getStartedPlugins()){
            sb.append(Core.universalObjectMapper.writeValueAsString(pluginWrapper.getDescriptor()));
        }
        return sb.toString();

    }

}
