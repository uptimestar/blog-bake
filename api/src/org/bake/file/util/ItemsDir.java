package org.bake.file.util;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.info.util.Confd;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class ItemsDir {

	static final Confd P = Confd.INSTANCE;
	private static final Logger _log = LoggerFactory.getLogger(ItemsDir.class);

	public static final String ITEM = "item";


	public static String[] EXTS = { "info", "pug" };

	static String PROJ_ROOT;

	static {
		try {
			PROJ_ROOT= P.getConf("proj_root");
		} catch (Throwable e) {
			_log.error(e.getMessage(),e);
		}
	}

	/**
	 * Starting w/ root, list recursive dirs that contain meta.info and index.pug
	 *
	 * @param basedir
	 * @return
	 */
	public static Collection<String> fileList(final String basedir) throws Throwable {

		String dirName = PROJ_ROOT + basedir;

		File dir = new File(dirName);

		Collection<File> lst;
		Set<String> lst2 = new HashSet<String>();

		try {
			lst = FileUtils.listFiles(dir, EXTS, true);
			for(File f: lst) {
				String path =  f.getPath();
				path = path.replace('\\','/');
				int pos = path.lastIndexOf('/');
				path = path.substring(0,pos);
				path = path.replace(PROJ_ROOT,"");
				lst2.add(path);
			}
		} catch (Throwable e) {
			_log.warn(e.toString());
			return new HashSet();
		}
		return lst2;
	}

	protected static Collection<File> listFiles() {
		Collection<File> lst;
		File dir = null;
		try {
			lst = FileUtils.listFiles(dir, null, true);
		} catch (Throwable e) {
			_log.warn(e.toString());
			return new ArrayList();
		}
		return lst;
	}

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
				_log.info(file);
				row.put(ITEM, file);
				ret.add(row);
			} catch (Throwable e) {
				e.printStackTrace();
			} //t
		} //for
		return ret;
	}//()

	/**
	 * ['First post', 'content/blog/2013/second-post', 123, 'post', 'ok'] ,
	 * ['First post', 'content/blog/2013/second-post', 123, 'post', 'ok']
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
