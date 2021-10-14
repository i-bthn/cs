package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0004\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0007\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0001H\n\u001a\r\u0010\t\u001a\u00020\u0003*\u00020\u0003H\n¨\u0006\n"}, d2 = {"div", "Ljava/math/BigDecimal;", "other", "Ljava/math/BigInteger;", "minus", "mod", "plus", "rem", "times", "unaryMinus", "kotlin-stdlib"}, k = 5, mv = {1, 1, 8}, xi = 1, xs = "kotlin/MathKt")
/* compiled from: BigNumbers.kt */
public class MathKt__BigNumbersKt {
    @InlineOnly
    private static final BigInteger plus(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger add = bigInteger.add(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(other)");
        return add;
    }

    @InlineOnly
    private static final BigInteger minus(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger subtract = bigInteger.subtract(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(other)");
        return subtract;
    }

    @InlineOnly
    private static final BigInteger times(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger multiply = bigInteger.multiply(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return multiply;
    }

    @InlineOnly
    private static final BigInteger div(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger divide = bigInteger.divide(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(other)");
        return divide;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger remainder = bigInteger.remainder(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigInteger unaryMinus(@NotNull BigInteger bigInteger) {
        BigInteger negate = bigInteger.negate();
        Intrinsics.checkExpressionValueIsNotNull(negate, "this.negate()");
        return negate;
    }

    @InlineOnly
    private static final BigDecimal plus(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        BigDecimal add = bigDecimal.add(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(other)");
        return add;
    }

    @InlineOnly
    private static final BigDecimal minus(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        BigDecimal subtract = bigDecimal.subtract(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(other)");
        return subtract;
    }

    @InlineOnly
    private static final BigDecimal times(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        BigDecimal multiply = bigDecimal.multiply(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return multiply;
    }

    @InlineOnly
    private static final BigDecimal div(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        BigDecimal divide = bigDecimal.divide(bigDecimal2, RoundingMode.HALF_EVEN);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(other, RoundingMode.HALF_EVEN)");
        return divide;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use rem(other) instead", replaceWith = @ReplaceWith(expression = "rem(other)", imports = {}))
    @InlineOnly
    private static final BigDecimal mod(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        BigDecimal remainder = bigDecimal.remainder(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigDecimal rem(@NotNull BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        BigDecimal remainder = bigDecimal.remainder(bigDecimal2);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(@NotNull BigDecimal bigDecimal) {
        BigDecimal negate = bigDecimal.negate();
        Intrinsics.checkExpressionValueIsNotNull(negate, "this.negate()");
        return negate;
    }
}
