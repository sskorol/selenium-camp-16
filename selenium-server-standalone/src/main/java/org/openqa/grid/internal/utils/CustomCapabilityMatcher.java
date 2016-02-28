package org.openqa.grid.internal.utils;

import java.util.Map;
import java.util.function.BiPredicate;

import static java.util.Objects.nonNull;

public class CustomCapabilityMatcher extends DefaultCapabilityMatcher {

	private static final String NODE_ID = "nodeId";

	private final BiPredicate<Object, Object> areEqual = (ob1, ob2) ->
			nonNull(ob1) && nonNull(ob2) && ob1.toString().equals(ob2.toString());

	@Override
	public boolean matches(Map<String, Object> nodeCapability,
	                       Map<String, Object> reqCapability) {
		return super.matches(nodeCapability, reqCapability) &&
				areEqual.test(nodeCapability.get(NODE_ID), reqCapability.get(NODE_ID));
	}
}
