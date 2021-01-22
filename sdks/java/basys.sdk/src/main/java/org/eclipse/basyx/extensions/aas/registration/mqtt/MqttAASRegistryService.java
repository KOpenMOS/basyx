package org.eclipse.basyx.extensions.aas.registration.mqtt;

import java.util.List;

import org.eclipse.basyx.aas.metamodel.map.descriptor.AASDescriptor;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.aas.registration.api.IAASRegistryService;
import org.eclipse.basyx.extensions.shared.mqtt.MqttEventService;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation variant for the AASRegistryService that triggers MQTT events for
 * different operations on the registry. Has to be based on a backend
 * implementation of the IAASRegistryService to forward its method calls.
 * 
 * @author haque
 *
 */
public class MqttAASRegistryService extends MqttEventService implements IAASRegistryService {
	private static Logger logger = LoggerFactory.getLogger(MqttAASRegistryService.class);

	// List of topics
	public static final String TOPIC_REGISTERAAS = "Registry_registeredAAS";
	public static final String TOPIC_REGISTERSUBMODEL = "Registry_registeredSubmodel";
	public static final String TOPIC_DELETEAAS = "Registry_deletedAAS";
	public static final String TOPIC_DELETESUBMODEL = "Registry_deletedSubmodel";

	// The underlying AASRegistryService
	protected IAASRegistryService observedRegistryService;
	
	/**
	 * Constructor for adding this MQTT extension on top of an AASRegistryService
	 * 
	 * @param observedRegistryService the underlying registry service 
	 * @param serverEndpoint endpoint of mqtt broker
	 * @param clientId unique client identifier
	 * @throws MqttException
	 */
	public MqttAASRegistryService(IAASRegistryService observedRegistryService, String serverEndpoint, String clientId) throws MqttException {
		super(serverEndpoint, clientId);
		logger.info("Create new MQTT AAS Registry Service for endpoint " + serverEndpoint);
		this.observedRegistryService = observedRegistryService;
	}

	/**
	 * Constructor for adding this MQTT extension on top of an AASRegistryService
	 * 
	 * @param observedRegistryService the underlying registry service 
	 * @param serverEndpoint endpoint of mqtt broker
	 * @param clientId unique client identifier
	 * @param user username for authentication with broker
	 * @param pw password for authentication with broker
	 * @throws MqttException
	 */
	public MqttAASRegistryService(IAASRegistryService observedRegistryService, String serverEndpoint, String clientId, String user, char[] pw)
			throws MqttException {
		super(serverEndpoint, clientId, user, pw);
		logger.info("Create new MQTT AAS Registry Service for endpoint " + serverEndpoint);
		this.observedRegistryService = observedRegistryService;
	}
	
	/**
	 * Constructor for adding this MQTT extension on top of an AASRegistryService
	 * 
	 * @param observedRegistryService the underlying registry service 
	 * @param client already configured client
	 * @throws MqttException
	 */
	public MqttAASRegistryService(IAASRegistryService observedRegistryService, MqttClient client) throws MqttException {
		super(client);
		logger.info("Create new MQTT AAS Registry Service for endpoint " + client.getServerURI());
		this.observedRegistryService = observedRegistryService;
	}

	
	@Override
	public void register(AASDescriptor deviceAASDescriptor) throws ProviderException {
		this.observedRegistryService.register(deviceAASDescriptor);
		sendMqttMessage(TOPIC_REGISTERAAS, deviceAASDescriptor.getIdentifier().getId());	
	}

	@Override
	public void register(IIdentifier aas, SubmodelDescriptor smDescriptor) throws ProviderException {
		this.observedRegistryService.register(aas, smDescriptor);
		sendMqttMessage(TOPIC_REGISTERSUBMODEL, concatAasSmId(aas, smDescriptor.getIdentifier()));
	}

	@Override
	public void delete(IIdentifier aasId) throws ProviderException {
		this.observedRegistryService.delete(aasId);
		sendMqttMessage(TOPIC_DELETEAAS, aasId.getId());
	}

	@Override
	public void delete(IIdentifier aasId, IIdentifier smId) throws ProviderException {
		this.observedRegistryService.delete(aasId, smId);
		sendMqttMessage(TOPIC_DELETESUBMODEL, concatAasSmId(aasId, smId));
	}

	@Override
	public AASDescriptor lookupAAS(IIdentifier aasId) throws ProviderException {
		return this.observedRegistryService.lookupAAS(aasId);
	}

	@Override
	public List<AASDescriptor> lookupAll() throws ProviderException {
		return this.observedRegistryService.lookupAll();
	}

	@Override
	public List<SubmodelDescriptor> lookupSubmodels(IIdentifier aasId) throws ProviderException {
		return this.observedRegistryService.lookupSubmodels(aasId);
	}

	@Override
	public SubmodelDescriptor lookupSubmodel(IIdentifier aasId, IIdentifier smId) throws ProviderException {
		return this.observedRegistryService.lookupSubmodel(aasId, smId);
	}
	
	public static String concatAasSmId(IIdentifier aasId, IIdentifier smId) {
		return "(" + aasId.getId() + "," + smId.getId() + ")";
	}
}
