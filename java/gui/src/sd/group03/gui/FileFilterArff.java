package sd.group03.gui;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class FileFilterArff extends FileFilter {

    //Accept only .arff-files
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = UtilsFileFilter.getExtension(f);
        if (extension != null) {
            if (extension.equals(UtilsFileFilter.arff)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return ".arff-Dateien";
    }
}

