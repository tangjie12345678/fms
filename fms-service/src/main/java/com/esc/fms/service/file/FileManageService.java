package com.esc.fms.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by tangjie on 2017/9/7.
 */
public interface FileManageService {

    boolean fileReplace(Integer fileID, MultipartFile sourceFiles, String userName) throws IOException;

    boolean fileDelete(List<Integer> fileIDs,String userName) throws IOException;
}
