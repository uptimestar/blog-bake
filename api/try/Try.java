import org.bake.srv.util.File;
import org.bake.srv.util.FilesDir;
import org.info.util.Confd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Try {
	private static final Logger _log = LoggerFactory.getLogger(Try.class);
	static Confd P = Confd.INSTANCE;

	public static void main(String[] args) throws Throwable{
		_log.info("starting");

		Optional item = File.read("/blog/one");

		System.out.println(item);

		if(true) return;
		var items =	FilesDir.fileList("");

		System.out.println(items);
	}

}
