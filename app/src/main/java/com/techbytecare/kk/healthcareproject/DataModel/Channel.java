package com.techbytecare.kk.healthcareproject.DataModel;

/**
 * Created by kundan on 3/1/2018.
 */

public class Channel {

    private String id;

    private String updated_at;

    private String field2;

    private String name;

    private String created_at;

    private String last_entry_id;

    private String longitude;

    private String latitude;

    private String field1;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getField2 ()
    {
        return field2;
    }

    public void setField2 (String field2)
    {
        this.field2 = field2;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getLast_entry_id ()
    {
        return last_entry_id;
    }

    public void setLast_entry_id (String last_entry_id)
    {
        this.last_entry_id = last_entry_id;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getField1 ()
    {
        return field1;
    }

    public void setField1 (String field1)
    {
        this.field1 = field1;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", updated_at = "+updated_at+", field2 = "+field2+", name = "+name+", created_at = "+created_at+", last_entry_id = "+last_entry_id+", longitude = "+longitude+", latitude = "+latitude+", field1 = "+field1+"]";
    }
}
