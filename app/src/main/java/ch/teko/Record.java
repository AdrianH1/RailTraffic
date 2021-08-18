package ch.teko;

import java.sql.Date;

public class Record {
    private String datasetid;
    private String recordid;
    private Field fields;
    private String record_timestamp;

    public Field getFields () {
        return fields;
    }
}
