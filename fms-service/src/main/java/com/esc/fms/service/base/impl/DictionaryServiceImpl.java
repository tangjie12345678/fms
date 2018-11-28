package com.esc.fms.service.base.impl;

import com.esc.fms.dao.DictionaryMapper;
import com.esc.fms.entity.Dictionary;
import com.esc.fms.service.base.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjie on 2016/12/9.
 */

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    public List<Dictionary> selectByDictType(String dictType) {
        return dictionaryMapper.selectByDictType(dictType);
    }
}
