/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.basyx.components.registry;

import javax.servlet.http.HttpServlet;

import org.eclipse.basyx.components.IComponent;
import org.eclipse.basyx.components.configuration.BaSyxContextConfiguration;
import org.eclipse.basyx.components.configuration.BaSyxMongoDBConfiguration;
import org.eclipse.basyx.components.configuration.BaSyxSQLConfiguration;
import org.eclipse.basyx.components.registry.configuration.BaSyxRegistryConfiguration;
import org.eclipse.basyx.components.registry.configuration.RegistryBackend;
import org.eclipse.basyx.components.registry.servlet.InMemoryRegistryServlet;
import org.eclipse.basyx.components.registry.servlet.MongoDBRegistryServlet;
import org.eclipse.basyx.components.registry.servlet.SQLRegistryServlet;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxHTTPServer;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic registry that can start and stop a registry with different kinds of backends.
 * Currently supports MongoDB and SQL. For development purposes, the component can also start a
 * registry without a backend and without persistency.
 * 
 * @author espen
 *
 */
public class RegistryComponent implements IComponent {
	private static Logger logger = LoggerFactory.getLogger(RegistryComponent.class);
	
	// The server with the servlet that will be created
	private BaSyxHTTPServer server;

	// The component configuration
	private BaSyxContextConfiguration contextConfig;
	private BaSyxRegistryConfiguration registryConfig;

	// The backend configuration
	private BaSyxMongoDBConfiguration mongoDBConfig;
	private BaSyxSQLConfiguration sqlConfig;

	/**
	 * Default constructor that loads default configurations
	 */
	public RegistryComponent() {
		contextConfig = new BaSyxContextConfiguration();
		registryConfig = new BaSyxRegistryConfiguration();
	}

	/**
	 * Constructor with given configuration for the registry and its server context. This constructor will create an
	 * InMemory registry.
	 * 
	 * @param contextConfig The context configuration
	 */
	public RegistryComponent(BaSyxContextConfiguration contextConfig) {
		this.contextConfig = contextConfig;
		this.registryConfig = new BaSyxRegistryConfiguration(RegistryBackend.INMEMORY);
	}

	/**
	 * Constructor with given configuration for the registry and its server context. This constructor will create a
	 * registry with a MongoDB backend.
	 * 
	 * @param contextConfig The context configuration
	 * @param mongoDBConfig The mongoDB configuration
	 */
	public RegistryComponent(BaSyxContextConfiguration contextConfig, BaSyxMongoDBConfiguration mongoDBConfig) {
		this.contextConfig = contextConfig;
		this.registryConfig = new BaSyxRegistryConfiguration(RegistryBackend.MONGODB);
		this.mongoDBConfig = mongoDBConfig;
	}

	/**
	 * Constructor with given configuration for the registry and its server context. This constructor will create a
	 * registry with an SQL backend.
	 * 
	 * @param contextConfig The context configuration
	 * @param sqlConfig     The sql configuration
	 */
	public RegistryComponent(BaSyxContextConfiguration contextConfig, BaSyxSQLConfiguration sqlConfig) {
		this.contextConfig = contextConfig;
		this.registryConfig = new BaSyxRegistryConfiguration(RegistryBackend.SQL);
		this.sqlConfig = sqlConfig;
	}

	/**
	 * Constructor with given configuration for the registry and its server context.
	 * Will load the backend configuration using the default load process.
	 * 
	 * @param contextConfig  The context configuration
	 * @param registryConfig The registry configuration
	 */
	public RegistryComponent(BaSyxContextConfiguration contextConfig, BaSyxRegistryConfiguration registryConfig) {
		this.contextConfig = contextConfig;
		this.registryConfig = registryConfig;
	}

	/**
	 * Starts the context at http://${hostName}:${port}/${path}
	 */
	@Override
	public void startComponent() {
		BaSyxContext context = contextConfig.createBaSyxContext();
		context.addServletMapping("/*", loadRegistryServlet());
		server = new BaSyxHTTPServer(context);
		server.start();
		logger.info("Registry server started");
	}

	/**
	 * Loads a registry with a backend according to the registryConfig
	 * 
	 * @return
	 */
	private HttpServlet loadRegistryServlet() {
		HttpServlet registryServlet = null;
		RegistryBackend backendType = registryConfig.getRegistryBackend();
		switch(backendType) {
			case MONGODB:
				registryServlet = loadMongoDBRegistryServlet();
				break;
			case SQL:
				registryServlet = loadSQLRegistryServlet();
				break;
			case INMEMORY:
				registryServlet = loadInMemoryRegistryServlet();
				break;
		}
		return registryServlet;
	}

	/**
	 * Creates a registry servlet with an sql backend
	 * 
	 * @return
	 */
	private HttpServlet loadSQLRegistryServlet() {
		logger.info("Loading SQLRegistry");
		BaSyxSQLConfiguration config;
		if (this.sqlConfig == null) {
			config = new BaSyxSQLConfiguration();
			config.loadFromDefaultSource();
		} else {
			config = this.sqlConfig;
		}
		return new SQLRegistryServlet(config);
	}

	/**
	 * Creates a registry servlet with an mongodb backend
	 * 
	 * @return
	 */
	private HttpServlet loadMongoDBRegistryServlet() {
		logger.info("Loading MongoDBRegistry");
		BaSyxMongoDBConfiguration config;
		if (this.mongoDBConfig == null) {
			config = new BaSyxMongoDBConfiguration();
			config.loadFromDefaultSource();
		} else {
			config = this.mongoDBConfig;
		}
		return new MongoDBRegistryServlet(config);
	}

	/**
	 * Creates an registry servlet with in memory data (=> not persistent)
	 * 
	 * @return
	 */
	private HttpServlet loadInMemoryRegistryServlet() {
		logger.info("Loading InMemoryRegistry");
		return new InMemoryRegistryServlet();
	}

	@Override
	public void stopComponent() {
		server.shutdown();
		logger.info("Registry server stopped");
	}
}
