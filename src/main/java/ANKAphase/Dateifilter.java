package ANKAphase;

import java.io.File;
import java.io.FilenameFilter;


public class Dateifilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
        return (name.toLowerCase().endsWith(".tif") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".tiff")
        		|| name.toLowerCase().endsWith(".edf") || name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".png")
        		|| name.toLowerCase().endsWith(".jpeg"));
    }
}