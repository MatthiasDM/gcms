/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.config;

/**
 *
 * @author Matthias
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sdm.gcms.services.SpringPluginService;



@Configuration
public class WebConfigurer implements WebMvcConfigurer {
	
	@Autowired
	private SpringPluginService springPluginStarter;

}
