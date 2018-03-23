package org.bake.srv;

import java.util.Map;

import org.apache.chain.srv.Ctx;
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
	public boolean exec(Ctx ctx) {
		logger.info("auth");
		return false;
	}

}
