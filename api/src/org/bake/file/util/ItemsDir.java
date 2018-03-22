package org.bake.file.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

public class ItemsDir {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemsDir.class);

	protected static Collection<File> listFiles() {
		Collection<File> lst;
		File dir = null;
		try {
			lst = FileUtils.listFiles(dir, null, true);
		} catch (Throwable e) {
			LOGGER.warn(e.toString());
			return new ArrayList();
		}
		return lst;
	}

	public static final String ITEM = "item";

	public static File makeItem(String folder, String fn) throws Throwable {
		String fullFn = null;


		File f = new File(fullFn);

		Files.createParentDirs(f);
		Files.touch(f);

		return f;
	}

	public static List<Map> list() {
		List<Map> ret = new ArrayList();
		for (File f : listFiles()) {
			try {
				Map row = null;// Item.read(f);
				row.remove("body");
				row.remove("orig");
				row.remove("tags");
				String file = f.toString();
				file = file.replace(null, "");
				file = FilenameUtils.removeExtension(file);
				LOGGER.info(file);
				row.put(ITEM, file);
				ret.add(row);
			} catch (Throwable e) {
				e.printStackTrace();
			} //t
		} //for
		return ret;
	}//()

	/**
	   ['First post', 'content/blog/2013/second-post', 123, 'post', 'ok'] ,
	   ['First post', 'content/blog/2013/second-post', 123, 'post', 'ok']
	*/
	public static String jsList() {
		//remap
		List<Map> olist = list();
		List<List> ret = new ArrayList();
		for (Map row : olist) {
			ret.add(arow(row));
		}
		JSONArray list = new JSONArray();
		list.addAll(ret);
		return list.toJSONString();
	}

	protected static List arow(Map row) {
		List lst = new ArrayList();
		lst.add(row.get("title"));
		lst.add(row.get(ITEM));
		lst.add(row.get("date"));
		lst.add(row.get("type"));
		lst.add(row.get("status"));

		return lst;
	}

}//class
