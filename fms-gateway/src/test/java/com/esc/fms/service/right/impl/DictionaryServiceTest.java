package com.esc.fms.service.right.impl;

import com.esc.fms.entity.Dictionary;
import com.esc.fms.service.base.DictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2016/12/9.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DictionaryServiceTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    public void testSelectByDictType()
    {
        String dictType="性别";
        List<Dictionary> dictList = new ArrayList<Dictionary>();
        dictList = dictionaryService.selectByDictType(dictType);
        System.out.println("test");
    }
}
