import org.bake.srv.StartSrv;
import org.info.util.Confd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	static final Confd P = Confd.INSTANCE;
	private static final Logger _log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Throwable {
		_log.info("starting");

		StartSrv.start();


	}

}
