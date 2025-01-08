package dev.twerklife.client.modules.player;

import dev.twerklife.api.manager.module.Module;
import dev.twerklife.api.manager.module.RegisterModule;
import dev.twerklife.api.utilities.*;
import dev.twerklife.client.events.EventMotion;
import dev.twerklife.client.values.impl.*;

@RegisterModule(name = "MCP", tag = "MCP", description = "Throws a Pearl by clicking Middle Click", category = Module.Category.PLAYER)
public class ModuleMCP extends Module {
    ValueEnum itemSwitch = new ValueEnum("Item", "Item", "The item to place the blocks with.", ItemModes.Pearl);
    ValueEnum switchMode = new ValueEnum("Strict", "Strict", "Determines if the Client switches to the Pearl in a Legit way.", SwitchMode.Silent);

    public void onMotion(EventMotion event) {
        if (this.nullCheck()) {
            return;
        }
        int slot = InventoryUtils.getTargetSlot(this.itemSwitch.getValue().toString());
        int lastSlot = mc.player.getInventory().selectedSlot;

        if (slot == -1) {
            ChatUtils.sendMessage("No Pearls could be found.", "MCP");
        } else {
            InventoryUtils.switchSlot(slot, this.switchMode.getValue().equals(InventoryUtils.SwitchModes.Silent));

            if (!this.switchMode.getValue().equals(InventoryUtils.SwitchModes.Strict)) {
                InventoryUtils.switchSlot(lastSlot, this.switchMode.getValue().equals(InventoryUtils.SwitchModes.Silent));
            }
        }
    }

    public enum SwitchMode {
        Silent,
        Strict
    }

    public enum ItemModes {
        Pearl,
        Firework
    }
}
