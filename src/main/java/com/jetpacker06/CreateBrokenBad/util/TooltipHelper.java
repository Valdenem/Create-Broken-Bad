package com.jetpacker06.CreateBrokenBad.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;

public class TooltipHelper {
    private static final int MAIN_COLOR = 0xC7984B;
    private static final int HIGHLIGHT_COLOR = 0xEEDA78;
    private static final Style MAIN_STYLE = Style.EMPTY.withColor(MAIN_COLOR);
    private static final Style HIGHLIGHT_STYLE = Style.EMPTY.withColor(HIGHLIGHT_COLOR);

    public static Component holdShift() {
        return Component.literal("Hold ")
                .withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.literal("[")
                .withStyle(ChatFormatting.DARK_GRAY)
                .append(Component.literal("Shift")
                .withStyle(Screen.hasShiftDown() ? ChatFormatting.WHITE : ChatFormatting.GRAY)
                .append(Component.literal("]")
                .withStyle(ChatFormatting.DARK_GRAY))
                .append(Component.literal(" for Summary")
                .withStyle(ChatFormatting.DARK_GRAY))));
    }

    public static List<Component> formattedTooltip(String translationKey) {
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(Component.empty());

        String[] parts = Component.translatable(translationKey).getString().split("_");
        boolean highlight = false;
        MutableComponent line = Component.literal("").withStyle(MAIN_STYLE);

        for (String part : parts) {
            line.append(Component.literal(part).withStyle(highlight ? HIGHLIGHT_STYLE : MAIN_STYLE));
            highlight = !highlight;
        }

        tooltip.add(line);
        return tooltip;
    }

    public static boolean isShiftKeyDown() {
        return Screen.hasShiftDown();
    }
}