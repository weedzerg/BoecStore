package com.security.filelocker.interfaces;


import com.security.filelocker.objects.InfoFile;

import java.util.ArrayList;

/**
 * Created by DaiPhongPC on 9/13/2017.
 */

public interface UploadDecodeFile {
    void uploadFile(ArrayList<InfoFile> lsFile);
    void uploadListview(ArrayList<InfoFile> lsFile);
}
