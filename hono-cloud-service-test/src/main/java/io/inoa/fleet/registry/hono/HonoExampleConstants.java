package io.inoa.fleet.registry.hono;

public class HonoExampleConstants {


	/**
	 * For devices signalling that they remain connected for an indeterminate amount
	 * of time, a command is periodically sent to the device after the following
	 * number of seconds elapsed.
	 */
	public static final int COMMAND_INTERVAL_FOR_DEVICES_CONNECTED_WITH_UNLIMITED_EXPIRY = Integer
			.parseInt(System.getProperty("command.repetition.interval", "5"));

	private HonoExampleConstants() {
		// prevent instantiation
	}
}
