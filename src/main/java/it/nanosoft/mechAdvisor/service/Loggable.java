package it.nanosoft.mechAdvisor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Loggable {
	
	Logger newloggerApp = LoggerFactory.getLogger(Loggable.class);

	public Logger logging();
}