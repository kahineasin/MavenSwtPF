package pf.java.pfHelper;

public class PFDataColumn {
    String key;
    Object value;
    private Class<?> _type;
 
    public PFDataColumn(String k, Object v) {
        key = k;
        value = v;
    }
    public PFDataColumn(String k, Class<?> type) {
        key = k;
        _type = type;
    }
 
    public String getKey() {
        return key;
    }
 
    public Object getValue() {
        return value;
    }
 
    public void setKey(String key) {
        this.key = key;
    }
 
    public void setValue(Object value) {
        this.value = value;
    }
    public Class<?> getDataType() {
        return _type;
    }
}
