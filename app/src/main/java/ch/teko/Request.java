package ch.teko;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class Request {
    private String api_url = "https://data.sbb.ch/api/records/1.0/search/?dataset=rail-traffic-information&sort=published&pretty_print=true&rows=";
    
    public void doCall(String rows) {
        try {
            URL url = new URL(api_url + rows);
            URLConnection request = url.openConnection();
            request.connect();
    
            //InputStream input = url.openStream();
            InputStreamReader reader = new InputStreamReader((InputStream)request.getContent());

            Gson gson = new Gson();
            DataStructiure data = gson.fromJson(reader, DataStructiure.class);
            String xx = "asdf";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
