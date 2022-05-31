package com.example.accessingdatamysql.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbl_role")
@Entity
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    // mappedBy trỏ tới tên biến roles ở trong user.
    @ManyToMany(mappedBy = "roles")
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> users;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
