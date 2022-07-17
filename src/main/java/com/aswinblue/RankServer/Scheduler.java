package com.aswinblue.RankServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aswinblue.RankServer.Entity.UserInfoEntity;
import com.aswinblue.RankServer.Repository.searchNameRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Component
@Slf4j // private log 선언
public class Scheduler {
    @Autowired // String boot가 알아서 new 해서 사용하는 annotation
    searchNameRepository snr;

	@Scheduled(cron = "0 */5 * * * *")	// 5분마다
	public void scheduleEvery5Min() throws Exception {
        log.info("5 minute Scheduler start");

	}

	@Scheduled(cron = "0 0 4 * * *")	// 매일 4시 정각
	public void scheduleEvery4clock() throws Exception {
		log.info("4 o'clock Scheduler start");
		setRank();
	}

	// TODO : remove after test
	@Scheduled(cron = "0 * * * * *")	// 1분마다
	public void scheduleEveryMinute() throws Exception {
		log.info("every single minute");
		setRank();
	}
	
	private void setRank() {
		// rank 새로 매기기
		List<UserInfoEntity> data = snr.findAllByOrderByScoreDesc();
		int rank = 0;
		int same_score_count = 1;
		int prev_score = -1;
		for (UserInfoEntity row : data) {
			if (prev_score != row.getScore()) {
				prev_score = row.getScore();
				rank += same_score_count;
				same_score_count = 1;
				row.setRank(rank);
			}
			else {
				same_score_count++;
				row.setRank(rank);
			}
		}
		snr.saveAll(data);
	}
	
	/*
	@Scheduled(cron = "0 * * * * *")	// 1분마다
	public void test3() throws Exception {
		log.info("health check - " + env);
	}
    */
}
