package com.esc.fms.service.right;

import com.esc.fms.entity.SysMenu;

import java.util.List;

/**
 * Created by tangjie on 2016/12/5.
 */
public interface SysMenuService {

    List<SysMenu> selectByUserName(String userName);

}
