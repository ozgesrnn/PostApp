package com.project.postapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)//postu çektiğimde hemen ilgili user objesi gelmeyecek performans açısından
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //user ı silince postta kaybolsun
    @JsonIgnore
    User user;
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;
}

