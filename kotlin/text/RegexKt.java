package kotlin.text;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public final class RegexKt {
    private static final <T extends Enum<T> & FlagEnum> Set<T> fromInt(int i) {
        Intrinsics.reifiedOperationMarker(4, "T");
        EnumSet allOf = EnumSet.allOf(Enum.class);
        CollectionsKt.retainAll(allOf, new RegexKt$fromInt$$inlined$apply$lambda$1(i));
        Set<T> unmodifiableSet = Collections.unmodifiableSet(allOf);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiable… == it.value }\n        })");
        return unmodifiableSet;
    }

    public static final MatchResult findNext(Matcher matcher, int i, CharSequence charSequence) {
        if (!matcher.find(i)) {
            return null;
        }
        return new MatcherMatchResult(matcher, charSequence);
    }

    public static final MatchResult matchEntire(Matcher matcher, CharSequence charSequence) {
        if (!matcher.matches()) {
            return null;
        }
        return new MatcherMatchResult(matcher, charSequence);
    }

    public static final IntRange range(MatchResult matchResult) {
        return new IntRange(matchResult.start(), matchResult.end() - 1);
    }

    public static final IntRange range(MatchResult matchResult, int i) {
        return new IntRange(matchResult.start(i), matchResult.end(i) - 1);
    }

    public static final int toInt(Iterable<? extends FlagEnum> iterable) {
        int i = 0;
        for (FlagEnum flagEnum : iterable) {
            i |= flagEnum.getValue();
        }
        return i;
    }
}
