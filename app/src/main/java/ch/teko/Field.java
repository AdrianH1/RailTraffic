package ch.teko;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Field {
    private String validitybegin;
    private String description;
    private String description_html;
    private String title;
    private String author;
    private String validityend;
    private String link;
    private String published;


    public String getDate () {
        String date = new String();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssXXX");
            
            Date d = formatter.parse(validitybegin); 
            formatter.applyPattern("dd.MM.yyyy");
            date = formatter.format(d);
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public String getTime () {
        String time = new String();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssXXX");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date t = formatter.parse(validitybegin); 
            formatter.applyPattern("HH:mm");
            time = formatter.format(t);
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return time;
    }

    public String getTitle () {
        return title;
    }

    public String getDescription () {
        return description;
    }
}
