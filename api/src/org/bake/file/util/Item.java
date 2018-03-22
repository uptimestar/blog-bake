package org.bake.file.util;

import org.apache.commons.io.FileUtils;
import org.info.rpc.EMsg;
import org.info.rpc.J;
import org.info.util.Confd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * title=Second Post
 * date=2013-08-25
 * type=post
 * tags=blog
 * status=published
 * ~~~~~~
 */
public class Item {

	public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static final Logger LOGGER = LoggerFactory.getLogger(Item.class);

	public static String readJs(String path) {
		File f = new File(path);
		Map m;
		try {
			m = read(f);
		} catch (Throwable e) {
			LOGGER.warn(e.getMessage());
			m = new HashMap();
			m.put(EMsg.ERROR, e.getClass());
		}
		return J.toJ(m);
	}

	/**
	 * Read a meta info file
	 *
	 * @param f
	 * @return
	 * @throws Throwable
	 */
	public static Map read(File f) throws Throwable {
		LOGGER.info(f.toString());
		String fn = "";

		var item = Confd.loadProps(fn);

		if (item.containsKey("date")) {
			Date d = (Date) item.get("date");
			item.put("date", d.getTime());//as long
		}
		if (item.containsKey("tags")) {
			String[] tags = (String[]) item.get("tags");
			String joined = String.join(",", tags);
			item.put("tags", joined);
		}
		return item;
	}

	public static Map write(File f, Date dat, String title, String type, String tags, String status, String body)
		throws Throwable {

		String date = df.format(dat);

		StringBuilder txt = new StringBuilder();
		add(txt, "title", title);
		add(txt, "date", date);
		add(txt, "type", type);
		add(txt, "tags", tags);
		add(txt, "status", status);
		txt.append(body);

		FileUtils.writeStringToFile(f, txt.toString(), StandardCharsets.UTF_8);
		return read(f);
	}

	protected static void add(StringBuilder frm, String key, String val) {
		frm.append(key + "=" + val + "\n");
	}


}//class
