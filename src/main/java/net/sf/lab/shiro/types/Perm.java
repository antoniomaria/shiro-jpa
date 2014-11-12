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

    public static String getLiteral(int  mask) {
        Perm values[] = Perm.values();

        List<String> actions = new ArrayList<String>();
        int idx = 0;
        while (idx < values.length && mask > 0) {
            Perm value = values[idx];
            if ((mask & value.getMask()) == value.getMask()) {
                actions.add(value.literal);
                mask = mask | value.getMask();
            }
            idx++;
        }

        StringBuilder builder = new StringBuilder();
        for (idx = 0; idx < actions.size(); idx++) {
            builder.append(actions.get(idx));
            if (idx != actions.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

}
