package com.mcdragonmasters.PotatoSK.skript.effects;

import ch.njol.skript.lang.VariableString;
import ch.njol.skript.util.StringMode;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.util.Kleenean;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static com.mcdragonmasters.PotatoSK.PotatoSK.instance;

import static org.bukkit.Bukkit.getServer;

// Documentation
@Name("Command With Permission")
@Description("Make a Player execute a command with a permission")
@Examples({ "none rn \t test" })
@Since("0.1.0")

public class EffCommandWithPermission extends Effect{

    private static final HashMap<Permissible, String> map = new HashMap<>();

    static {
        Skript.registerEffect(EffCommandWithPermission.class,
                "execute %player% command %string% (with|using) %bukkitpermission%"
        );
    }
    private Expression<String> command;
    private Expression<Permission> perm;
    private Expression<Player> senders;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        senders = (Expression<Player>) exprs[0];
        command = (Expression<String>) exprs[1];
        perm = (Expression<Permission>) exprs[2];
        command = VariableString.setStringMode(command, StringMode.COMMAND);
        return true;
    }
    @Override
    protected void execute(@NotNull Event e) {
        if (senders != null && perm != null) {
            Permission perm = this.perm.getSingle(e);
            String command = this.command.getSingle(e);
            if (senders == null || command == null){
                return;
            }
            for (Player p : senders.getArray(e)) {
                map.put(p, command);
                PermissionAttachment attachment = new PermissionAttachment(instance, p);
                assert perm != null;
                attachment.setPermission(perm, true);
                p.performCommand(command);
                attachment.remove();
                map.remove(p, command);
            }
        }
    }



    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "execute " + senders + " command " + command + "with permission " + perm; // In case of using a variable, use your variable as "yourvariable.toString(e, debug)"
    }

}

