package org.bake.api03;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.apache.chain.srv.CCtx;
import org.apache.chain.srv.ICmd;
import org.bake.srv.util.FilesDir;
import org.info.net.NetU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AggregateCmd implements ICmd {
	private static final Logger LOGGER = LoggerFactory.getLogger(AggregateCmd.class);

	@Override
	public boolean exec(CCtx ctx) {

		String ret = FilesDir.jsList();
		LOGGER.info(ret);

		// make a response
		ByteBuf body = NetU.stringToBy(ret);
		FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, body);
		resp.headers().add("Access-Control-Allow-Origin", "*");
		ctx.httpResponse(resp);
		return true; //done
	}

}
