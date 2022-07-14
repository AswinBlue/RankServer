package com.aswinblue.RankServer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.aswinblue.RankServer.Entity.UserInfoEntity;

// CrudRepository<관리대상, 대표값의 type>
// public interface searchNameRepository extends CrudRepository<UserInfoEntity, Long> {
public interface searchNameRepository extends JpaRepository<UserInfoEntity, Long> {
    @Query("select u from UserInfoEntity u")
    List<UserInfoEntity> findByName(String name);

    // find top 100 Rank
    List<UserInfoEntity> findFirst100ByOrderByRankAsc();
    
    // find by score
    // List<UserInfoEntity> findByScoreLessThanEqual(Integer score);
    List<UserInfoEntity> findFirst20ByRankGreaterThanEqualOrderByScoreDesc(Integer score);
    List<UserInfoEntity> findFirst20ByRankLessThanOrderByScoreDesc(Integer score);
    
    // rank all by score
    List<UserInfoEntity> findAllByOrderByScoreDesc();
}