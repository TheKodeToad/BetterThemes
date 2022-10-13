package io.toadlabs.betterthemes.transformer;

import org.objectweb.asm.tree.*;

/**
 * Transforms a ClassNode. No inputs are <code>null</code>.
 */
public interface Transformer {

	boolean isApplicable(String className);

	void transform(ClassNode clazz);

}
