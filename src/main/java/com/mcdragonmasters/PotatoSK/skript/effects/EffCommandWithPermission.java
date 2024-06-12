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

    static {
        Skript.registerEffect(EffCommandWithPermission.class,
                "execute %player% command %string% with permission %string%"
        );
    }
    private Expression<String> command;
    private Expression<String> perm;
    private Expression<Player> effplayer;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        effplayer = (Expression<Player>) exprs[0];
        command = (Expression<String>) exprs[1];
        perm = (Expression<String>) exprs[2];
        return true;
    }
    @Override
    protected void execute(@NotNull Event e) {
        String command = this.command.getSingle(e);
        String perm = this.perm.getSingle(e);
        Player player = this.effplayer.getSingle(e);
        assert command != null;
        assert perm != null;
        assert player != null;
        player.addAttachment(instance, perm, true, 1);
        player.performCommand(command);

    }



    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "execute " + effplayer + " command " + command + "with permission " + perm; // In case of using a variable, use your variable as "yourvariable.toString(e, debug)"
    }

}

