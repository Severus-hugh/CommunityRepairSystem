package com.repair.repair_system2;

import com.repair.repair_system2.entity.Community;
import com.repair.repair_system2.mapper.CommunityMapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@MapperScan(basePackages = "com.repair.repair_system2.mapper")
public class RepairSystem2Application {

    public static void main(String[] args) {
        SpringApplication.run(RepairSystem2Application.class, args);
    }

}
