import org.bake.srv.util.FilesDir;
import org.info.util.Confd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Try {
	private static final Logger _log = LoggerFactory.getLogger(Try.class);
	static Confd P = Confd.INSTANCE;

	public static void main(String[] args) throws Throwable{
		_log.info("starting");

		String files =	FilesDir.jsList("");

		System.out.println(files);
	}

}
