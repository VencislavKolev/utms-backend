package com.vmware.utms.cli.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class FileUtilImpl implements FileUtil {

    public FileUtilImpl() {
    }

    @Override
    public boolean checkIfExists(String filepath) {
        File file = new File(filepath);
        return file.isFile();
    }


    @Override
    public File getFile(String filepath) throws FileNotFoundException {
        boolean exists = this.checkIfExists(filepath);

        if (!exists) {
            throw new FileNotFoundException("File not found");
        }
        return new File(filepath);
    }

}
