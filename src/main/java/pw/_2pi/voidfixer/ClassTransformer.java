package pw._2pi.voidfixer;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] classTransformed) {
		if (transformedName.equals("net.minecraft.world.World")) {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(classTransformed);
			classReader.accept(classNode, 0);

			// find method
			for (MethodNode method : classNode.methods) {
				String mappedMethodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, method.name,
						method.desc);
				if (mappedMethodName.equals("func_72919_O") || mappedMethodName.equals("getHorizon")) {
					AbstractInsnNode targetNode = null;
					for (AbstractInsnNode instruction : method.instructions.toArray()) {

						if (instruction.getOpcode() == ALOAD && instruction.getNext().getOpcode() == GETFIELD) {
							targetNode = instruction;
							break;

						}
					}
					if (targetNode != null) {
						InsnList insnList = new InsnList();
						insnList.add(new InsnNode(DCONST_0));
						insnList.add(new InsnNode(DRETURN));

						for (int i = 0; i < 3; i++) {
							targetNode = targetNode.getNext();
							method.instructions.remove(targetNode.getPrevious());
						}

						method.instructions.insertBefore(targetNode, insnList);
						break;
					} else {
						System.out.println("Error finding bytecode in method");
						return classTransformed;
					}
				}
			}
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);

			return classWriter.toByteArray();

		}
		return classTransformed;

	}
}
