package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0005\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\b\u001a\r\u0010\n\u001a\u00020\u000b*\u00020\u0003H\b\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u0010\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\u0003H\b\u001a\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0014\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\b\u001a\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0016*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\r*\u00020\u0003H\b\u001a\u0015\u0010\u0019\u001a\u00020\r*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010\u001a\u001a\u0004\u0018\u00010\r*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001b\u001a\u001b\u0010\u001a\u001a\u0004\u0018\u00010\r*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u001c\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0003H\b\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u001e*\u00020\u0003H\u0007¢\u0006\u0002\u0010 \u001a\u001b\u0010\u001f\u001a\u0004\u0018\u00010\u001e*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010!\u001a\r\u0010\"\u001a\u00020#*\u00020\u0003H\b\u001a\u0015\u0010\"\u001a\u00020#*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010$\u001a\u0004\u0018\u00010#*\u00020\u0003H\u0007¢\u0006\u0002\u0010%\u001a\u001b\u0010$\u001a\u0004\u0018\u00010#*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010&\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020\r2\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020\u001e2\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020#2\u0006\u0010\f\u001a\u00020\rH\b¨\u0006("}, d2 = {"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBoolean", "", "toByte", "", "radix", "", "toByteOrNull", "(Ljava/lang/String;)Ljava/lang/Byte;", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLong", "", "toLongOrNull", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShort", "", "toShortOrNull", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "toString", "kotlin-stdlib"}, k = 5, mv = {1, 1, 8}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: StringNumberConversions.kt */
class StringsKt__StringNumberConversionsKt extends StringsKt__StringBuilderKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(byte b, int i) {
        String num = Integer.toString(b, CharsKt.checkRadix(CharsKt.checkRadix(i)));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(short s, int i) {
        String num = Integer.toString(s, CharsKt.checkRadix(CharsKt.checkRadix(i)));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(int i, int i2) {
        String num = Integer.toString(i, CharsKt.checkRadix(i2));
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(long j, int i) {
        String l = Long.toString(j, CharsKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return l;
    }

    @InlineOnly
    private static final boolean toBoolean(@NotNull String str) {
        return Boolean.parseBoolean(str);
    }

    @InlineOnly
    private static final byte toByte(@NotNull String str) {
        return Byte.parseByte(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte toByte(@NotNull String str, int i) {
        return Byte.parseByte(str, CharsKt.checkRadix(i));
    }

    @InlineOnly
    private static final short toShort(@NotNull String str) {
        return Short.parseShort(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short toShort(@NotNull String str, int i) {
        return Short.parseShort(str, CharsKt.checkRadix(i));
    }

    @InlineOnly
    private static final int toInt(@NotNull String str) {
        return Integer.parseInt(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int toInt(@NotNull String str, int i) {
        return Integer.parseInt(str, CharsKt.checkRadix(i));
    }

    @InlineOnly
    private static final long toLong(@NotNull String str) {
        return Long.parseLong(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long toLong(@NotNull String str, int i) {
        return Long.parseLong(str, CharsKt.checkRadix(i));
    }

    @InlineOnly
    private static final float toFloat(@NotNull String str) {
        return Float.parseFloat(str);
    }

    @InlineOnly
    private static final double toDouble(@NotNull String str) {
        return Double.parseDouble(str);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return StringsKt.toByteOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str, int i) {
        int intValue;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Integer intOrNull = StringsKt.toIntOrNull(str, i);
        if (intOrNull == null || (intValue = intOrNull.intValue()) < -128 || intValue > 127) {
            return null;
        }
        return Byte.valueOf((byte) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return StringsKt.toShortOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str, int i) {
        int intValue;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Integer intOrNull = StringsKt.toIntOrNull(str, i);
        if (intOrNull == null || (intValue = intOrNull.intValue()) < -32768 || intValue > 32767) {
            return null;
        }
        return Short.valueOf((short) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return StringsKt.toIntOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String str, int i) {
        boolean z;
        int i2;
        int i3;
        boolean z2;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        CharsKt.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i4 = 0;
        char charAt = str.charAt(0);
        int i5 = -2147483647;
        if (charAt >= '0') {
            i2 = 0;
            z = false;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                z2 = true;
                i5 = Integer.MIN_VALUE;
            } else if (charAt != '+') {
                return null;
            } else {
                z2 = false;
            }
            z = z2;
            i2 = 1;
        }
        int i6 = i5 / i;
        int i7 = length - 1;
        if (i2 <= i7) {
            while (true) {
                int digitOf = CharsKt.digitOf(str.charAt(i2), i);
                if (digitOf >= 0 && i4 >= i6 && (i3 = i4 * i) >= i5 + digitOf) {
                    i4 = i3 - digitOf;
                    if (i2 == i7) {
                        break;
                    }
                    i2++;
                } else {
                    return null;
                }
            }
        }
        return z ? Integer.valueOf(i4) : Integer.valueOf(-i4);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return StringsKt.toLongOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String str, int i) {
        int i2;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        CharsKt.checkRadix(i);
        int length = str.length();
        Long l = null;
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char charAt = str.charAt(0);
        long j = -9223372036854775807L;
        if (charAt >= '0') {
            i2 = 0;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                j = Long.MIN_VALUE;
                i3 = 1;
            } else if (charAt != '+') {
                return null;
            }
            i2 = i3;
            i3 = 1;
        }
        long j2 = (long) i;
        long j3 = j / j2;
        long j4 = 0;
        int i4 = length - 1;
        if (i3 <= i4) {
            while (true) {
                int digitOf = CharsKt.digitOf(str.charAt(i3), i);
                if (digitOf >= 0 && j4 >= j3) {
                    long j5 = j4 * j2;
                    long j6 = (long) digitOf;
                    if (j5 >= j + j6) {
                        j4 = j5 - j6;
                        if (i3 == i4) {
                            break;
                        }
                        i3++;
                        l = null;
                    } else {
                        return null;
                    }
                } else {
                    return l;
                }
            }
        }
        return i2 != 0 ? Long.valueOf(j4) : Long.valueOf(-j4);
    }

    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsKt(String str, Function1<? super String, ? extends T> function1) {
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return (T) function1.invoke(str);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Float toFloatOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return Float.valueOf(Float.parseFloat(str));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Double toDoubleOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return Double.valueOf(Double.parseDouble(str));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
