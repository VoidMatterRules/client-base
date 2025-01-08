package dev.twerklife.client.modules.movement;

import dev.twerklife.api.manager.module.Module;
import dev.twerklife.api.manager.module.RegisterModule;
import dev.twerklife.client.values.impl.ValueNumber;
import java.lang.reflect.Field;

@RegisterModule(name = "Step", tag = "Step", description = "Transports you up onto Blocks like a horse would.", category = Module.Category.MOVEMENT)
public class ModuleStep extends Module {
    private final ValueNumber StepHeight = new ValueNumber("StepHeight", "StepHeight", "Determines the amount of Blocks this will work on. (vertically)", 1f, 1f, 3f);

    @Override
    public void onDisable() {
        if (nullCheck()) return;
        try {
            Field stepHeightField = mc.player.getClass().getDeclaredField("stepHeight"); // Replace with obfuscated name if necessary
            stepHeightField.setAccessible(true);
            stepHeightField.set(mc.player, 0.6f);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdate() {
        if (nullCheck()) return;
        if (mc.player.isOnGround()) {
            try {
                Field stepHeightField = mc.player.getClass().getDeclaredField("stepHeight"); // Replace with obfuscated name if necessary
                stepHeightField.setAccessible(true);
                stepHeightField.set(mc.player, StepHeight.getValue().floatValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

