package org.bake.srv.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.bake.srv.StartSrv;
import org.info.util.Confd;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

public class FilesDir {

	public static final String ITEM = "item";
	static final Confd P = Confd.INSTANCE;
	private static final Logger _log = LoggerFactory.getLogger(FilesDir.class);
	public static String[] EXTS = {"info", "pug"};

	static String PROJ_ROOT;

	static {
		try {
			PROJ_ROOT = P.getConf("proj_root");
		} catch (Throwable e) {
			_log.error(e.getMessage(), e);
		}
	}


	public static File makeItem(String folder, String fn) throws Throwable {
		String fullFn = null;


		File f = new File(fullFn);

		Files.createParentDirs(f);
		Files.touch(f);

		return f;
	}

	/**
	 * Starting w/ root, list recursive dirs that contain meta.info and index.pug
	 *
	 * @param basedir
	 * @return
	 */
	public static Collection<String> fileListStr(final String basedir) throws Throwable {

		String dirName = PROJ_ROOT + basedir;

		File dir = new File(dirName);

		Collection<File> lst;
		Set<String> lst2 = new HashSet<String>();

		try {
			lst = FileUtils.listFiles(dir, EXTS, true);
			for (File f : lst) {
				String path = f.getPath();
				path = path.replace('\\', '/');
				int pos = path.lastIndexOf('/');
				path = path.substring(0, pos);
				path = path.replace(PROJ_ROOT, "");
				lst2.add(path);
			}
		} catch (Throwable e) {
			_log.warn(e.toString());
			return new HashSet();
		}
		return lst2;
	}

	public static Collection<Map> fileList(final String basedir) throws Throwable {

		Collection<String> fileStrList = fileListStr(basedir);
		Collection<Map> fileList = new ArrayList();
		for (String dir : fileStrList) {
			Optional<Map> infO = FileItem.read(dir);
			if (infO.isPresent()) {
				Map info = infO.get();
				if(info.size()>0)
				fileList.add(info);
			}
		}

		return fileList;
	}

	public static String jsList(final String basedir) {

		try {
			Collection<Map> fileList = fileList(basedir);
			JSONArray list = new JSONArray();
			list.addAll(fileList);
			return list.toJSONString();
		} catch (Throwable e) {
			_log.warn(e.getMessage(), e);
			return StartSrv.ERROR;
		}

	}

	/**
	 * ['First post', 'content/blog/2013/second-post', 123, 'post', 'ok'] ,
	 * ['First post', 'content/blog/2013/second-post', 123, 'post', 'ok']
	 */
	public static String jsListByFields() {
		//remap
		List<Map> olist = null;
		List<List> ret = new ArrayList();
		for (Map row : olist) {
			ret.add(null);
		}
		JSONArray list = new JSONArray();
		list.addAll(ret);
		return list.toJSONString();
	}




}//class
