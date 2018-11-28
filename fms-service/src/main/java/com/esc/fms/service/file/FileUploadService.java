package com.esc.fms.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by tangjie on 2017/4/6.
 */
public interface FileUploadService {

    boolean fileUpload(List<String> sharedStaffs, String shareType, List<String> opTypes, Integer fileType, String folderName, MultipartFile[] sourceFiles, String userName) throws IOException;
}
