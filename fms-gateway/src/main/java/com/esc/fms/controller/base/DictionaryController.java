package com.esc.fms.controller.base;

import com.esc.fms.entity.Dictionary;
import com.esc.fms.service.base.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by tangjie on 2016/12/9.
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/selectByDictType.do")
    @ResponseBody
    public List<Dictionary> selectByDictType(@RequestParam("dictType") String dictType)
    {
        return dictionaryService.selectByDictType(dictType);
    }


}
