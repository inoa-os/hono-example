package io.inoa.fleet.registry.hono;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application extends HonoExampleApplicationBase {

	/**
	 * The client for sending and receiving data is instantiated here.
	 *
	 * @param tenantId
	 */
	public Application(String tenantId) {
		super(tenantId);
	}

	public static void main(final String[] args) throws Exception {
		String tenantId = System.getenv("HONO_TENANT");
		System.out.println("Starting consumer...");
		final Application honoExampleApplication = new Application(tenantId);
		honoExampleApplication.consumeData();
		System.in.read();
		System.out.println("Finishing consumer.");
	}
}
