package xyz.rexlin600.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author rexlin600
 */
@Document(indexName = "rexlin600-user", type = "user", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String remark;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", remark=" + remark + "]";
    }
}