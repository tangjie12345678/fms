package com.esc.fms.dao;

import com.esc.fms.entity.Dictionary;

import java.util.List;

public interface DictionaryMapper {

    List<Dictionary> selectByDictType(String dictType);

}