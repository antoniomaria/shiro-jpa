package net.sf.lab.shiro.types;

import java.util.ArrayList;
import java.util.List;

public enum Perm {

    CREATE(1, "create"), READ(2, "read"), UPDATE(4, "update"), DELETE(8, "delete");

    private int mask;
    private String literal;

    Perm(int mask, String literal) {
        this.mask = mask;
        this.literal = literal;
    }

    public int getMask() {
        return mask;
    }

    public static String getLiteral(int mask) {
        Perm values[] = Perm.values();
        List<String> literals = new ArrayList<String>();
        for (int idx = 0; idx < values.length; idx++) {
            Perm value = values[idx];
            if ((mask & value.getMask()) == value.getMask()) {
                literals.add(value.literal);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int idx = 0; idx < literals.size(); idx++) {
            builder.append(literals.get(idx));
            if (idx != literals.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

}
