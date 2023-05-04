package sdm.gcms.cc.controller;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sdm.gcms.cc.services.SpringPluginService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PluginRequestController {

    
    @Autowired
    SpringPluginService pluginService;

        @Bean
    public GroupedOpenApi PluginOpenApi() {
        String paths[] = {"/api/**"};
        return GroupedOpenApi.builder().group("plugins").pathsToMatch(paths)
                //.pathsToExclude("/api/**")
                //.pathsToExclude("/api/v2/**", "/v2/**", "/**/v3/**", "/api/**")
                .build();
    }
    
    @GetMapping(value = "/info")
    public ResponseEntity<Object> pluginsInfo() throws JsonProcessingException {
        return new ResponseEntity<>(pluginService.pluginsInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/s/info")
    public ResponseEntity<Object> securedPluginsInfo(@RequestHeader("X-API-Key") String apikey) throws JsonProcessingException {
        return new ResponseEntity<>(pluginService.pluginsInfo(), HttpStatus.OK);
    }

    @PutMapping(value = "/reload/{id}")
    public ResponseEntity<Object> reloadPlugins(@PathVariable("id") String id) {
        return new ResponseEntity<>(pluginService.reloadPlugin(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/plugin/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPlugin(@PathVariable("id") String id) {
        return new ResponseEntity<>(pluginService.getPlugin(id), HttpStatus.OK);
    }
 

}

//package sdm.gcms.cc.controller;
//
//
//import java.util.Collection;
//
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//
//
//
///**
// *
// * @author Matthias
// */
//@Controller
//public class TomcatRequestController {
//
//    @GetMapping("/cc/info")
//    public ResponseEntity<Collection<String>> sayHello() {
//        return ResponseEntity.ok().body(IntStream.range(0, 10)
//                .mapToObj(i -> "Hello number " + i)
//                .collect(Collectors.toList()));
//    }
//
////    @GetMapping(value = "api/reload")
////    public ResponseEntity<Object> reloadPlugins() {
////        Plugins.reloadPlugins();
////        return new ResponseEntity<>(new StringBuilder("Plugins reloaded"), HttpStatus.ACCEPTED);
////    }
////
////    @GetMapping(value = "api/{id}/")
////    public ResponseEntity<Object> getPlugin(@PathVariable("id") String id) {
////        PluginInterface commandExtension = Plugins.getExtension(id);
////        return new ResponseEntity<>(new StringBuilder("Plugin " + id + " is active"), HttpStatus.CREATED);
////    }
////
////    @PostMapping(path = "api/{id}/{command}",
////            consumes = MediaType.APPLICATION_JSON_VALUE,
////            produces = MediaType.APPLICATION_JSON_VALUE)
////    public ResponseEntity<Object> doPluginPost(@PathVariable("id") String id, @RequestBody Map<String, String> parameters) {
////        if (Config.checkAPIKey(parameters)) {
////            //CommandExtension commandExtension = Plugins.getExtension(id);
////            //return new ResponseEntity<>(commandExtension.doCommand(parameters), HttpStatus.CREATED);
////           return new ResponseEntity<>("Apikey not valid", HttpStatus.FORBIDDEN);
////        } else {
////            return new ResponseEntity<>("Apikey not valid", HttpStatus.FORBIDDEN);
////        }
////    }
//
////    @GetMapping(path = "cc/api/{id}/{command}")
////    public ResponseEntity<Object> doPluginGet(@PathVariable("id") String id, @PathVariable("command") String command) {
////        CommandExtension commandExtension = Plugins.getExtension(id);
////        Map<String, String> parameters = new HashMap<>();
////        parameters.put("command", "info");
////        return new ResponseEntity<>(commandExtension.doCommand(parameters), HttpStatus.CREATED);
////    }
//
//}
