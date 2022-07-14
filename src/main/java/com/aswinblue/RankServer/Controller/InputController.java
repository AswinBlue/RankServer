package com.aswinblue.RankServer.Controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aswinblue.RankServer.DTO.UserInfoDTO;
import com.aswinblue.RankServer.Entity.UserInfoEntity;
import com.aswinblue.RankServer.Repository.searchNameRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;

@Controller  // controller를 정의하는 annotation
@Slf4j // private log 선언
public class InputController {
    @Autowired // String boot가 알아서 new 해서 사용하는 annotation
    searchNameRepository snr;

    // TODO : remove after test
    @GetMapping("data/test") 
    public String test(Model model) {
        // test code
        List<UserInfoEntity> samples = new ArrayList<UserInfoEntity>();
        samples.add(new UserInfoEntity(null, "test1", 10));
        samples.add(new UserInfoEntity(null, "test2", 10));
        samples.add(new UserInfoEntity(null, "test3", 20));
        samples.add(new UserInfoEntity(null, "test4", 30));
        samples.add(new UserInfoEntity(null, "test5", 15));
        snr.saveAll(samples);

        List<UserInfoEntity> allUsers = snr.findAllByOrderByScoreDesc();
        model.addAttribute("allUsers", allUsers);

        return "rank_table";
    }

    @GetMapping("/data/rank")
    public String showRankTable(Model model) {
        List<UserInfoEntity> allUsers = snr.findAllByOrderByScoreDesc();
        model.addAttribute("allUsers", allUsers);

        return "rank_table";
    }

    @PostMapping("/data/userRank")
    public String handleUserNameForm(@RequestBody String userName, Model model) {
        // 이름으로 점수 찾고, 새로운 페이지에서 랭크 테이블 보여줘야함
        
        // UserInfoEntity entity = dto.toEntity();
        // List<UserInfoEntity> user = snr.findByName(entity.getName());

        UserInfoEntity user = snr.findByName(userName).get(0);
        log.debug("user: {}", user);
        // TODO : Fix this
        List<UserInfoEntity> allUsers = snr.findFirst20ByRankGreaterThanEqualOrderByScoreDesc(user.getScore());
        allUsers.addAll(snr.findFirst20ByRankLessThanOrderByScoreDesc(user.getScore()));

        model.addAttribute("targetUser", user);
        model.addAttribute("allUsers", allUsers);
        return "rank_table";
    }

    // to send non-entity value to view
    /*
    public ModelAndView showUserForm()  
    {
        ModelAndView mv= new ModelAndView("page");
        UserInfoEntity user = new UserInfoEntity();
        mv.getModel.put("user",user);
        List<UserInfoEntity> users;
        mv.getModel().put("users",users);
        return mv;
    }
    */
}