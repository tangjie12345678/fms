package com.esc.fms.service.base;

import com.esc.fms.entity.Dictionary;

import java.util.List;

/**
 * Created by tangjie on 2016/12/9.
 */
public interface DictionaryService {

    List<Dictionary> selectByDictType(String dictType);

}
