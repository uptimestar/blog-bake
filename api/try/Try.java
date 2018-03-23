import org.bake.file.util.Item;
import org.bake.file.util.ItemsDir;
import org.info.util.Confd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Try {
	private static final Logger _log = LoggerFactory.getLogger(Try.class);
	static Confd P = Confd.INSTANCE;

	public static void main(String[] args) throws Throwable{
		_log.info("starting");

		Optional item = Item.read("/blog/one");

		System.out.println(item);

		if(true) return;
		var items =	ItemsDir.fileList("");

		System.out.println(items);
	}

}
