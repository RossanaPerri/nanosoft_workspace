package it.nanosoft.mechAdvisor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * L'interfaccia Loggable dichiara un oggetto Logger che può essere usato dalle
 * classi che implementano Loggable per tenere traccia, stampando su console (o
 * inviando messaggi ad una determinata destinazione) durante il funzionamento
 * dell'applicazione, di quanto sta avvenendo nel corso dell'esecuzione.
 * 
 * @author RossanaPerri
 *
 */

public interface Loggable {

	Logger newloggerApp = LoggerFactory.getLogger(Loggable.class);

	public Logger logging();
}