package org.eclipse.basyx.regression.AASServer;

import org.eclipse.basyx.aas.aggregator.api.IAASAggregator;
import org.eclipse.basyx.components.aas.mongodb.MongoDBAASAggregator;
import org.eclipse.basyx.components.configuration.BaSyxContextConfiguration;
import org.eclipse.basyx.testsuite.regression.aas.aggregator.AASAggregatorSuite;

public class TestMongoDBAggregator extends AASAggregatorSuite {

	@Override
	protected IAASAggregator getAggregator() {
		MongoDBAASAggregator aggregator = new MongoDBAASAggregator(BaSyxContextConfiguration.DEFAULT_CONFIG_PATH);
		aggregator.reset();

		return aggregator;
	}
}
