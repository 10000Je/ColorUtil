package io.github.u10000je.colorUtil;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.*;

import javax.annotation.Nullable;
import java.util.regex.Pattern;

public class ColorUtil {

    private static final String regex = "((?<=%1$s([0-9a-fA-Fk-oK-OrR]|#[0-9a-fA-F]{6}))|(?=%1$s([0-9a-fA-Fk-oK-OrR]|#[0-9a-fA-F]{6})))";
    private static final String match = "%s([0-9a-fA-Fk-oK-OrR]|#[0-9a-fA-F]{6})";

    /**
     * Translates color codes and formatting codes in a given message, applying them
     * to the text to create a formatted component.
     *
     * @param delimiter the character used to denote the start of a color or formatting code
     * @param message the input string containing the raw message with color or formatting codes
     * @return a {@link Component} instance representing the formatted message with
     *         the appropriate colors and decorations applied
     */
    public static Component translateColorCodes(String delimiter, String message) {
        String[] list = message.split(String.format(regex, delimiter));
        TextComponent.Builder textBuilder = Component.text();
        if (message.isEmpty()) {
            return textBuilder.build();
        }
        TextColor color = null;
        TextDecoration decoration = null;
        for (String str : list) {
            if (Pattern.matches(String.format(match, delimiter), str)) {
                if (str.charAt(1) == '#') {
                    color = getTextColor(str.substring(2));
                    decoration = null;
                }
                else {
                    char code = str.charAt(1);
                    if (getTextColor(code) != null) {
                        color = getTextColor(code);
                        decoration = null;
                    }
                    if (getTextDecoration(code) != null) {
                        decoration = getTextDecoration(code);
                    }
                    if (code == 'r') {
                        color = null;
                        decoration = null;
                    }
                }
            }
            else {
                textBuilder.append(getComponent(str, color, decoration));
            }
        }
        return textBuilder.build();
    }

    /**
     * Creates a {@link Component} with the specified text content, color, and optional text decoration.
     * If no text decoration is provided (i.e., null), the component will only include the text and color.
     *
     * @param content the text content of the component
     * @param color the {@link TextColor} to be applied to the text
     * @param decoration the {@link TextDecoration} to be applied to the text, or null if no decoration is needed
     * @return a {@link Component} instance with the specified text, color, and optional decoration
     */
    public static Component getComponent(String content, @Nullable TextColor color, @Nullable TextDecoration decoration) {
        if(decoration == null) {
            return Component.text(content, color);
        }
        else {
            return Component.text(content, color, decoration);
        }
    }

    /**
     * Returns the corresponding {@link TextColor} for a given character code.
     * The provided code should match predefined color codes. If the code does not match,
     * the method returns null.
     *
     * @param code the character representing a specific color code
     * @return the {@link TextColor} corresponding to the given code, or null if the code is invalid
     */
    @Nullable
    public static TextColor getTextColor(char code) {
        return switch(Character.toLowerCase(code)) {
            case '0' -> NamedTextColor.BLACK;
            case '1' -> NamedTextColor.DARK_BLUE;
            case '2' -> NamedTextColor.DARK_GREEN;
            case '3' -> NamedTextColor.DARK_AQUA;
            case '4' -> NamedTextColor.DARK_RED;
            case '5' -> NamedTextColor.DARK_PURPLE;
            case '6' -> NamedTextColor.GOLD;
            case '7' -> NamedTextColor.GRAY;
            case '8' -> NamedTextColor.DARK_GRAY;
            case '9' -> NamedTextColor.BLUE;
            case 'a' -> NamedTextColor.GREEN;
            case 'b' -> NamedTextColor.AQUA;
            case 'c' -> NamedTextColor.RED;
            case 'd' -> NamedTextColor.LIGHT_PURPLE;
            case 'e' -> NamedTextColor.YELLOW;
            case 'f' -> NamedTextColor.WHITE;
            default -> null;
        };
    }

    /**
     * Converts a hexadecimal string into a {@link TextColor} object.
     * The input string should represent a valid hexadecimal color code.
     * If the string does not represent a valid hexadecimal color code, null is returned.
     *
     * @param hex the hexadecimal string representing the color code
     * @return the {@link TextColor} object corresponding to the color code, or null if the input is invalid
     */
    @Nullable
    public static TextColor getTextColor(String hex) {
        try {
            int code = Integer.parseInt(hex, 16);
            return TextColor.color(code);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Returns the corresponding {@link TextDecoration} for a given character code.
     * The provided code should match predefined decoration codes. If the code does not match,
     * the method returns null.
     *
     * @param code the character representing a specific text decoration code
     * @return the {@link TextDecoration} corresponding to the given code, or null if the code is invalid
     */
    @Nullable
    public static TextDecoration getTextDecoration(char code) {
        return switch(code) {
            case 'k' -> TextDecoration.OBFUSCATED;
            case 'l' -> TextDecoration.BOLD;
            case 'm' -> TextDecoration.STRIKETHROUGH;
            case 'n' -> TextDecoration.UNDERLINED;
            case 'o' -> TextDecoration.ITALIC;
            default -> null;
        };
    }

}