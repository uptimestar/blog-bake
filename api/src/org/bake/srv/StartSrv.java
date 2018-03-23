package org.bake.srv;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.apache.chain.DefaultChainRouter;
import org.apache.chain.srv.AbsNRouter;
import org.apache.chain.srv.ChainPipe;
import org.info.util.Confd;

public class StartSrv {
	protected static EventLoopGroup _eg = new NioEventLoopGroup(200);
	static Confd P = Confd.INSTANCE;

	public static void start() throws Throwable {

		AbsNRouter chain = new DefaultChainRouter("org.bake.api", new AuthFilterPre());

		int port = P.getConfI("port");
		new ChainPipe(chain).srvBoot(port, _eg);

		// now open http://localhost:8081/Blank
	}
}
