package io.toadlabs.betterthemes.transformer;

import java.util.*;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.osgi.framework.hooks.weaving.*;

public final class TransformersHook implements WeavingHook {

	private final List<Transformer> transformers = new ArrayList<>();

	{
//		transformers.add(new IBeamTransformer());
	}

	@Override
	public void weave(WovenClass wovenClass) {
		boolean any = transformers.stream().anyMatch((transformer) -> transformer.isApplicable(wovenClass.getClassName()));

		if(!any) {
			return;
		}

		ClassReader reader = new ClassReader(wovenClass.getBytes());
		ClassNode clazz = new ClassNode();
		reader.accept(clazz, 0);

		for(Transformer transformer : transformers) {
			if(!transformer.isApplicable(wovenClass.getClassName())) {
				continue;
			}

			transformer.transform(clazz);
		}

		ClassWriter writer = new ClassWriter(0);
		clazz.accept(writer);
		wovenClass.setBytes(writer.toByteArray());
	}

}
