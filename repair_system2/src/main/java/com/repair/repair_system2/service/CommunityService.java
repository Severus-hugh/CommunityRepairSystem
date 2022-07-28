package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Community;
import com.repair.repair_system2.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService extends ServiceImpl<CommunityMapper,Community> {
    public boolean saveCommunity(Community community) {

        return saveOrUpdate(community);

//        if(community.getCommunityName()==null){
//            return save(community);    //mybatis-plus提供的方法，表示插入数据
//        }else{
//            return updateById(community);
//        }
    }

//    @Autowired
//    private CommunityMapper communityMapper;
//
//    public int save(Community community){
//
//        if(community.getCommunityId()==null){   //ID不存在，新增
//           return communityMapper.insert(community);
//        }else{                                  //ID存在，更新
//           return communityMapper.update(community);
//        }
//    }
    public String getNameById(int id){
        Community community = baseMapper.selectById(id);
        return community.getCommunityName();
    }

}
