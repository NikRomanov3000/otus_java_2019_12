package MyJdbcOrm.core.model;

import MyJdbcOrm.core.annotations.Id;

public class Account {
    @Id
    private long id;
    private String type;
    private int n_attribute_value;
    private String s_attribute_value;

    public Account(long id, String type, int n_attribute_value, String s_attribute_value) {
        this.id = id;
        this.type = type;
        this.n_attribute_value = n_attribute_value;
        this.s_attribute_value = s_attribute_value;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getN_attribute_value() {
        return n_attribute_value;
    }

    public String getS_attribute_value() {
        return s_attribute_value;
    }
}
