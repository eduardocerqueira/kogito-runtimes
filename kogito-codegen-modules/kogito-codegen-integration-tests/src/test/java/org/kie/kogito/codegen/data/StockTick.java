package org.kie.kogito.codegen.data;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class StockTick {
    private String company;
    private long timestamp;
    private long value;

    public StockTick() {
    }

    public StockTick(String company, long value, long timestamp) {
        this.company = company;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getCompany() {
        return company;
    }

    public long getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
