/*
 * JBoss, Home of Professional Open Source.
 * Copyright (C) 2008 Red Hat, Inc.
 * Copyright (C) 2000-2007 MetaMatrix, Inc.
 * Licensed to Red Hat, Inc. under one or more contributor 
 * license agreements.  See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package com.metamatrix.data.api;

import com.metamatrix.data.exception.ConnectorException;

/**
 * <p>The primary entry point for a Connector.  This interface should be implemented
 * by the connector writer.</p>
 * 
 * <p>The Connector Manager will instantiate the implementation
 * of this class by reflection in an isolated classloader.  Once the class has been 
 * instantiated, the {@link #initialize(ConnectorEnvironment)} method will be called
 * with all necessary connector properties.  The {@link #start()} and {@link #stop()} 
 * methods are lifecycle methods called when starting or stopping the connector.</p>  
 *  
 * <p>
 */
public interface Connector {

    /**
     * Initialize the connector with the connector environment.  The environment
     * provides access to external resources the connector implementation may
     * need to use.  
     * @param environment The connector environment, provided by the Connector Manager
     * @throws ConnectorException If an error occurs during initialization
     */
    void initialize( ConnectorEnvironment environment ) throws ConnectorException;

    /**
     * Start the connector and prepare it to execute commands.
     * @throws ConnectorException
     */
    void start() throws ConnectorException;

    /**
     * Stop the connector.  No commands will be executed on the connector when it is
     * stopped.
     */
    void stop();

    /**
     * Obtain a connection with the connector.  The connection typically is associated
     * with a particular security context.  The connection is assumed to be pooled in 
     * the underlying source if pooling is necessary - the connection will be closed 
     * when execution has completed against it.  
     * @param context The context of the current MetaMatrix user that will be using this connection 
     * @return A Connection, created by the Connector
     * @throws ConnectorException If an error occurred obtaining a connection
     */
    Connection getConnection( SecurityContext context ) throws ConnectorException;

}
