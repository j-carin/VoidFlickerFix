package pw._2pi.voidfixer;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({ "pw._2pi.voidfixer" })
public class FMLLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { "pw._2pi.voidfixer.ClassTransformer" };
	}

	@Override
	public String getModContainerClass() {
		return "pw._2pi.voidfixer.VoidFixerContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		int memes = 32;
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
