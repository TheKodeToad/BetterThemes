package io.toadlabs.betterthemes.transformer;

import org.objectweb.asm.tree.*;

public interface Transformer {

	boolean isApplicable(String className);

	void transform(ClassNode clazz);

}
