import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CustomFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".amazons") || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "AMAZONS files (*.amazons)";
    }
}