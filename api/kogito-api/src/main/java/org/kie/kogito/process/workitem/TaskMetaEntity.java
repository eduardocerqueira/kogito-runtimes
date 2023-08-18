package org.kie.kogito.process.workitem;

import java.io.Serializable;
import java.util.Date;

public class TaskMetaEntity<K extends Serializable, T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;
    private K id;
    protected T content;
    protected Date updatedAt;
    protected String updatedBy;

    public TaskMetaEntity() {
    }

    public TaskMetaEntity(K id, String user) {
        this.id = id;
        this.updatedBy = user;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public K getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return id.equals(((TaskMetaEntity<K, T>) obj).id);
    }

    @Override
    public String toString() {
        return "id=" + id + ", content=" + content + ", updatedAt=" + updatedAt + ", updatedBy=" +
                updatedBy;
    }

}
