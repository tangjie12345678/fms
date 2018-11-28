package com.esc.fms.service.right.impl;

import com.esc.fms.dao.SysMenuMapper;
import com.esc.fms.entity.SysMenu;
import com.esc.fms.service.right.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjie on 2016/12/5.
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public List<SysMenu> selectByUserName(String userName) {
        return sysMenuMapper.selectByUserName(userName);
    }
}
