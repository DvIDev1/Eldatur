package de.Blackside.SkillSystem.Skills.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public class profileCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 0) {
                Player player = (Player)sender;
                Inventory inventory = Bukkit.createInventory(null, 3*9 , "Profile");
                for (int i = 0; i < inventory.getSize(); i++) {

                    if(inventory.getItem(i) == null) {
                        ItemStack glass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1 , (byte) 7 );
                        ItemMeta meta = glass.getItemMeta();
                        Objects.requireNonNull(meta).setDisplayName(" ");
                        glass.setItemMeta(meta);
                        inventory.setItem(i , new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1 , (byte) 7 ));

                        ItemStack PlayerSkull = new ItemStack(Material.LEGACY_SKULL_ITEM , 1 , (short) 3);
                        SkullMeta SkullMeta = (SkullMeta) PlayerSkull.getItemMeta();
                        Objects.requireNonNull(SkullMeta).setOwner(player.getName());
                        SkullMeta.setDisplayName("§4Profile");
                        PlayerSkull.setItemMeta(SkullMeta);
                        inventory.setItem(13 , PlayerSkull);
                    }

                }

                player.openInventory(inventory);
            }
        }
        return false;
    }


}
