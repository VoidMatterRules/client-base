package dev.twerklife.client.modules.movement;

import dev.twerklife.api.manager.module.Module;
import dev.twerklife.api.manager.module.RegisterModule;
import dev.twerklife.client.values.impl.ValueBoolean;

@RegisterModule(name = "Sprint", tag = "Sprint", description = "Always be sprinting.", category = Module.Category.MOVEMENT)
public class ModuleSprint extends Module {
    public static ValueBoolean Rage = new ValueBoolean("Rage", "Rage", "Turns Sprint always on, even if not moving. (Non Legit)", false);
    public static ValueBoolean InstantSpeed = new ValueBoolean("InstantSpeed", "InstantSpeed", "Basically Strafe but on Ground.", false);

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.nullCheck()) {
            return;
        }
        if (Rage.getValue()) {
            mc.player.setSprinting(true);
        } else {
            if (mc.player.forwardSpeed > 0.0f && !mc.player.horizontalCollision) {
                mc.player.setSprinting(true);
            }
        }
    }
}
