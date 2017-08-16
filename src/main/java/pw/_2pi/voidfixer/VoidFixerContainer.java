package pw._2pi.voidfixer;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class VoidFixerContainer extends DummyModContainer {
	public VoidFixerContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "voidfixer";
		meta.name = "VoidFixer";
		meta.description = "Fixes void flicker on Arcane";
		meta.version = "1.7.10";
		meta.authorList = Arrays.asList("2Pi");
		meta.credits = "some code taken from prplz's MouseDelayFix mod (with permission)";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
}
