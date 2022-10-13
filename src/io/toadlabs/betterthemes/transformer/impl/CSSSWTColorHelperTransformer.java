package io.toadlabs.betterthemes.transformer.impl;

import io.toadlabs.betterthemes.transformer.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

public class CSSSWTColorHelperTransformer implements Transformer {

	private static final String TARGET_DESC = "(Lorg/eclipse/swt/widgets/Control;Lorg/eclipse/swt/graphics/Color;)V";

	@Override
	public boolean isApplicable(String className) {
		return className.equals("org.eclipse.e4.ui.css.swt.helpers.CSSSWTColorHelper");
	}

	@Override
	public void transform(ClassNode clazz) {
		clazz.methods.stream()
				.filter((method) -> method.name.equals("setBackground")
						&& method.desc.equals(TARGET_DESC))
				.findFirst().ifPresent((setBackground) -> rewrite(setBackground, "Background"));
		clazz.methods.stream()
				.filter((method) -> method.name.equals("setForeground") && method.desc.equals(TARGET_DESC))
				.findFirst().ifPresent((setForeground) -> rewrite(setForeground, "Foreground"));
	}

	private void rewrite(MethodNode method, String type) {
		InsnList instructions = method.instructions;
		instructions.clear();
		LabelNode label0 = new LabelNode();
		instructions.add(label0);
		instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
		instructions.add(new TypeInsnNode(Opcodes.INSTANCEOF, "org/eclipse/swt/custom/StyledText"));
		LabelNode label1 = new LabelNode();
		instructions.add(new JumpInsnNode(Opcodes.IFEQ, label1));
		LabelNode label2 = new LabelNode();
		instructions.add(label2);
		instructions.add(new InsnNode(Opcodes.RETURN));
		instructions.add(label1);
		instructions.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
		instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
		instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/eclipse/swt/widgets/Control",
				"get" + type, "()Lorg/eclipse/swt/graphics/Color;", false));
		instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
		instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/util/Objects", "equals",
				"(Ljava/lang/Object;Ljava/lang/Object;)Z", false));
		LabelNode label3 = new LabelNode();
		instructions.add(new JumpInsnNode(Opcodes.IFNE, label3));
		LabelNode label4 = new LabelNode();
		instructions.add(label4);
		instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
		instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
		instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "org/eclipse/swt/widgets/Control",
				"set" + type, "(Lorg/eclipse/swt/graphics/Color;)V", false));
		instructions.add(label3);
		instructions.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
		instructions.add(new InsnNode(Opcodes.RETURN));
		LabelNode label5 = new LabelNode();
		instructions.add(label5);
	}

}
