package ch.teko;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Request {
    private String api_url = "https://data.sbb.ch/api/records/1.0/search/?dataset=rail-traffic-information&sort=published&pretty_print=true&rows=";
    ArrayList<Field> fields = new ArrayList<Field>();
    
    public ArrayList<Field> doCall(String rows) {
        try {
            URL url = new URL(api_url + rows);
            URLConnection request = url.openConnection();
            request.connect();
    
            //InputStream input = url.openStream();
            InputStreamReader reader = new InputStreamReader((InputStream)request.getContent());

            Gson gson = new Gson();
            DataStructure data = gson.fromJson(reader, DataStructure.class);
            
            fields.clear();

            for (Record record : data.getRecords()) {
                fields.add(record.getFields());
            }

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fields;
    }
}
