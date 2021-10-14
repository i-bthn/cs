package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;

public enum zzet {
    DOUBLE(0, zzev.SCALAR, zzfk.DOUBLE),
    FLOAT(1, zzev.SCALAR, zzfk.FLOAT),
    INT64(2, zzev.SCALAR, zzfk.LONG),
    UINT64(3, zzev.SCALAR, zzfk.LONG),
    INT32(4, zzev.SCALAR, zzfk.INT),
    FIXED64(5, zzev.SCALAR, zzfk.LONG),
    FIXED32(6, zzev.SCALAR, zzfk.INT),
    BOOL(7, zzev.SCALAR, zzfk.BOOLEAN),
    STRING(8, zzev.SCALAR, zzfk.STRING),
    MESSAGE(9, zzev.SCALAR, zzfk.MESSAGE),
    BYTES(10, zzev.SCALAR, zzfk.BYTE_STRING),
    UINT32(11, zzev.SCALAR, zzfk.INT),
    ENUM(12, zzev.SCALAR, zzfk.ENUM),
    SFIXED32(13, zzev.SCALAR, zzfk.INT),
    SFIXED64(14, zzev.SCALAR, zzfk.LONG),
    SINT32(15, zzev.SCALAR, zzfk.INT),
    SINT64(16, zzev.SCALAR, zzfk.LONG),
    GROUP(17, zzev.SCALAR, zzfk.MESSAGE),
    DOUBLE_LIST(18, zzev.VECTOR, zzfk.DOUBLE),
    FLOAT_LIST(19, zzev.VECTOR, zzfk.FLOAT),
    INT64_LIST(20, zzev.VECTOR, zzfk.LONG),
    UINT64_LIST(21, zzev.VECTOR, zzfk.LONG),
    INT32_LIST(22, zzev.VECTOR, zzfk.INT),
    FIXED64_LIST(23, zzev.VECTOR, zzfk.LONG),
    FIXED32_LIST(24, zzev.VECTOR, zzfk.INT),
    BOOL_LIST(25, zzev.VECTOR, zzfk.BOOLEAN),
    STRING_LIST(26, zzev.VECTOR, zzfk.STRING),
    MESSAGE_LIST(27, zzev.VECTOR, zzfk.MESSAGE),
    BYTES_LIST(28, zzev.VECTOR, zzfk.BYTE_STRING),
    UINT32_LIST(29, zzev.VECTOR, zzfk.INT),
    ENUM_LIST(30, zzev.VECTOR, zzfk.ENUM),
    SFIXED32_LIST(31, zzev.VECTOR, zzfk.INT),
    SFIXED64_LIST(32, zzev.VECTOR, zzfk.LONG),
    SINT32_LIST(33, zzev.VECTOR, zzfk.INT),
    SINT64_LIST(34, zzev.VECTOR, zzfk.LONG),
    DOUBLE_LIST_PACKED(35, zzev.PACKED_VECTOR, zzfk.DOUBLE),
    FLOAT_LIST_PACKED(36, zzev.PACKED_VECTOR, zzfk.FLOAT),
    INT64_LIST_PACKED(37, zzev.PACKED_VECTOR, zzfk.LONG),
    UINT64_LIST_PACKED(38, zzev.PACKED_VECTOR, zzfk.LONG),
    INT32_LIST_PACKED(39, zzev.PACKED_VECTOR, zzfk.INT),
    FIXED64_LIST_PACKED(40, zzev.PACKED_VECTOR, zzfk.LONG),
    FIXED32_LIST_PACKED(41, zzev.PACKED_VECTOR, zzfk.INT),
    BOOL_LIST_PACKED(42, zzev.PACKED_VECTOR, zzfk.BOOLEAN),
    UINT32_LIST_PACKED(43, zzev.PACKED_VECTOR, zzfk.INT),
    ENUM_LIST_PACKED(44, zzev.PACKED_VECTOR, zzfk.ENUM),
    SFIXED32_LIST_PACKED(45, zzev.PACKED_VECTOR, zzfk.INT),
    SFIXED64_LIST_PACKED(46, zzev.PACKED_VECTOR, zzfk.LONG),
    SINT32_LIST_PACKED(47, zzev.PACKED_VECTOR, zzfk.INT),
    SINT64_LIST_PACKED(48, zzev.PACKED_VECTOR, zzfk.LONG),
    GROUP_LIST(49, zzev.VECTOR, zzfk.MESSAGE),
    MAP(50, zzev.MAP, zzfk.VOID);
    
    private static final zzet[] zzahj;
    private static final Type[] zzahk = new Type[0];
    private final int id;
    private final zzfk zzahf;
    private final zzev zzahg;
    private final Class<?> zzahh;
    private final boolean zzahi;

    private zzet(int i, zzev zzev, zzfk zzfk) {
        this.id = i;
        this.zzahg = zzev;
        this.zzahf = zzfk;
        switch (zzes.zzafe[zzev.ordinal()]) {
            case 1:
                this.zzahh = zzfk.zzvd();
                break;
            case 2:
                this.zzahh = zzfk.zzvd();
                break;
            default:
                this.zzahh = null;
                break;
        }
        boolean z = false;
        if (zzev == zzev.SCALAR) {
            switch (zzes.zzaff[zzfk.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzahi = z;
    }

    public final int id() {
        return this.id;
    }

    static {
        zzet[] values = values();
        zzahj = new zzet[values.length];
        for (zzet zzet : values) {
            zzahj[zzet.id] = zzet;
        }
    }
}
