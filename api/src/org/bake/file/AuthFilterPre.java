package org.bake.file;

import java.util.Map;

import org.apache.chain.srv.CCtx;
import org.apache.chain.srv.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthFilterPre implements ICmd {
	private final static Logger logger = LoggerFactory.getLogger(AuthFilterPre.class);

	protected Map<String, Object> _sessions;

	/**
	 * return true to stop
	 */
	@Override
	public boolean exec(CCtx ctx) {
		logger.info("auth");
		return false;
	}

}
