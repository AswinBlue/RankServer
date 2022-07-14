package com.aswinblue.RankServer.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class UserInfoEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private Integer score;
    @Column
    private Long rank;

    public UserInfoEntity(Long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return "id: " + this.id + ", name: " + this.name + ", score: " + this.score + ", rank: " + this.rank + "\n";
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        return this.score;
    }

    public Long getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = Long.valueOf(rank);
    }
}