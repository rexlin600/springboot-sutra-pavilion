package xyz.rexlin600.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Web socket config
 *
 * @author hekunlin
 */
@Configuration
public class WebSocketConfig {

	/**
	 * Server endpoint exporter server endpoint exporter
	 *
	 * @return the server endpoint exporter
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}