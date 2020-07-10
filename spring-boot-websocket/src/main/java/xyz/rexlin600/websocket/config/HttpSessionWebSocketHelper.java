package xyz.rexlin600.websocket.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Http session web socket helper
 *
 * @author hekunlin
 */
public class HttpSessionWebSocketHelper extends ServerEndpointConfig.Configurator {

	/**
	 * Modify handshake *
	 *
	 * @param sec      sec
	 * @param request  request
	 * @param response response
	 */
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
	}
}