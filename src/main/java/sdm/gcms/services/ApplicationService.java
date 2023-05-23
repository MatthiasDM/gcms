/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.BasicDBObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import sdm.gcms.collections.Page;
import sdm.gcms.collections.PageAccess;
import sdm.gcms.shared.database.Core;
import sdm.gcms.shared.database.Database;
import static sdm.gcms.shared.database.Database.getSpecificObject;
import sdm.gcms.shared.database.Methods;
import sdm.gcms.shared.database.collections.MongoConfigurations;
import sdm.gcms.shared.database.filters.annotation.gcmsObject;
import sdm.gcms.shared.database.serializable.SerializableClass;
import sdm.gcms.shared.database.serializable.SerializableField;

/**
 *
 * @author Matthias
 */
@Service("ApplicationService")
public class ApplicationService {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AbstractAutowireCapableBeanFactory beanFactory;

    private static final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    public static StringBuilder getPage(String title) throws JsonProcessingException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        Page page = getSpecificObject(Database.getMongoConfiguration("pages"), "title", title, Page.class, Arrays.asList("access"));
        PageAccess pageAccess = page.getAccess();
        if(pageAccess.getType().equals("public")){
            return sb.append(Core.universalObjectMapper.writeValueAsString(page));
        }else{
            return sb;
        }        
    }

//    public static GetResponse dataload(String cookie, MongoConfigurations _mongoConf, Map<String, String> requestParameters, BasicDBObject prefilter) throws JsonProcessingException, ClassNotFoundException, NoSuchFieldException, IOException {
//        GetResponse response = new GetResponse();
//
//        SerializableClass serializableClass = Database.getSerializableClass(cookie, _mongoConf);
//        List<String> columns = getDocumentPriveleges(Methods.get, cookie, _mongoConf, true, serializableClass);
//
//        ArrayList<String> excludes = new ArrayList<>();
//        String sidx = _mongoConf.getIdName();
//        Integer sort = 1;
//        if (requestParameters.get("sidx") != null) {
//            sidx = String.valueOf(requestParameters.get("sidx")).equals("") ? _mongoConf.getIdName() : String.valueOf(requestParameters.get("sidx"));
//        }
//        if (requestParameters.get("sord") != null) {
//            sort = String.valueOf(requestParameters.get("sord")).equals("asc") ? 1 : -1;
//        }
//        if (requestParameters.get("excludes") != null) {
//            excludes.addAll(Arrays.asList(requestParameters.get("excludes")));
//        }
//        if (requestParameters.get("excludes[]") != null) {
//            String _excludes = requestParameters.get("excludes[]");
//            excludes.addAll(Arrays.asList(_excludes));
//        }
//        BasicDBObject filter = createFilterObject(requestParameters.get("filters"));
//        filter.forEach((k, v) -> prefilter.put(k, v));
//        Integer page = Integer.parseInt(requestParameters.get("page"));
//        Integer rows = Integer.parseInt(requestParameters.get("rows"));
//        Boolean includeLargeFields = false;
//        if (requestParameters.get("include_large_files") != null) {
//            includeLargeFields = Boolean.parseBoolean(requestParameters.get("include_large_files"));
//        }
//        for (SerializableField f : serializableClass.getFields()) {
//            gcmsObject annotation = (gcmsObject) f.getAnnotation();
//            if ((annotation.type().equals("cktext") || annotation.type().equals("ckcode")) && !includeLargeFields) {
//                excludes.add(f.getName());
//            }
//        }
//
//        BasicDBObject sorting = new BasicDBObject(sidx, sort);
//        ArrayList<Document> results = DatabaseActions.getObjectsRest(_mongoConf, filter, sorting, rows, excludes.toArray(new String[0]), columns, rows, page);
//
//        columns.removeAll(excludes);
//
//        results = DatabaseActions.loadRelationalColumns(columns, results, cookie, serializableClass);
//
//        response.setRecords(Integer.parseInt(String.valueOf(DatabaseActions.getObjectCount(_mongoConf, filter))));
//        double totalPage = (response.getRecords().doubleValue() / rows.doubleValue());
//        response.setTotal((int) (Math.ceil((totalPage))));
//        response.setPage(page);
//        if (!results.isEmpty()) {
//            response.setRows(results);
//        }
//
//        return response;
//    }
}
