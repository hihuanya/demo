package com.example.demo.controller;

import com.example.demo.bean.Energy;
import com.example.demo.bean.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.EnergyService;
import com.example.demo.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chuliuhuan
 * @date 2022-11-20 17:17
 */
@RestController
@RequestMapping("/index")
public class LoginController {

    JwtUtils jwtUitls = new JwtUtils();

    @Autowired
    EnergyService energyService;
    @Autowired
    StudentRepository studentRepository;


    @PutMapping("/login")
    public User  energy1(User user) {

        if(isEmpty(user.getName()) || isEmpty(user.getPwd())){
            new Exception("用户名和密码不能为空");
        }

        User admins  = studentRepository.findByNameAndPwd(user.getName(), user.getName());
        if (admins  !=null) {
            String token = JwtUtils.createToken(String.valueOf(admins.getId()), admins.getName());
            user.setToken(token);
            return user;
        }
        return null;
    }

    @PostMapping("/queryEnergyListByDate")
    public Map<String, Energy> queryEnergyListByDate(){
        return  energyService.queryEnergyListByDate();
    }


    public Boolean isEmpty(String arg){
        if (arg == null || arg.trim().isEmpty()) {
            return true;
        }
        return false;
    }


}
