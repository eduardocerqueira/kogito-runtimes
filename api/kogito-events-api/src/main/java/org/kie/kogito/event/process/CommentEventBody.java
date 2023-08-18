package org.kie.kogito.event.process;

import java.util.Date;
import java.util.Objects;

public class CommentEventBody {

    private String id;
    private String content;
    private Date updatedAt;
    private String updatedBy;

    private CommentEventBody() {
    }

    public static Builder create() {
        return new Builder(new CommentEventBody());
    }

    public String getContent() {
        return content;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CommentEventBody{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CommentEventBody that = (CommentEventBody) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(updatedBy,
                that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, updatedAt, updatedBy);
    }

    public Builder update() {
        return new Builder(this);
    }

    public static class Builder {

        private CommentEventBody instance;

        private Builder(CommentEventBody instance) {
            this.instance = instance;
        }

        public Builder id(String id) {
            instance.id = id;
            return this;
        }

        public Builder content(String content) {
            instance.content = content;
            return this;
        }

        public Builder updatedBy(String updatedBy) {
            instance.updatedBy = updatedBy;
            return this;
        }

        public Builder updatedAt(Date updatedAt) {
            instance.updatedAt = updatedAt;
            return this;
        }

        public CommentEventBody build() {
            return instance;
        }
    }
}
