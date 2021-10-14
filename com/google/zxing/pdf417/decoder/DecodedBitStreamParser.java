package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

/* access modifiers changed from: package-private */
public final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900 = new BigInteger[16];
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    /* access modifiers changed from: private */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        EXP900[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr = EXP900;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int i;
        StringBuilder sb = new StringBuilder(iArr.length * 2);
        Charset charset = DEFAULT_ENCODING;
        int i2 = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        for (int i3 = 2; i3 < iArr[0]; i3 = i + 1) {
            if (i2 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i2) {
                    case TEXT_COMPACTION_MODE_LATCH /*{ENCODED_INT: 900}*/:
                        i = textCompaction(iArr, i3, sb);
                        break;
                    case BYTE_COMPACTION_MODE_LATCH /*{ENCODED_INT: 901}*/:
                        i = byteCompaction(i2, iArr, charset, i3, sb);
                        break;
                    case NUMERIC_COMPACTION_MODE_LATCH /*{ENCODED_INT: 902}*/:
                        i = numericCompaction(iArr, i3, sb);
                        break;
                    default:
                        switch (i2) {
                            case MACRO_PDF417_TERMINATOR /*{ENCODED_INT: 922}*/:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*{ENCODED_INT: 923}*/:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /*{ENCODED_INT: 924}*/:
                                break;
                            case ECI_USER_DEFINED /*{ENCODED_INT: 925}*/:
                                i = i3 + 1;
                                break;
                            case ECI_GENERAL_PURPOSE /*{ENCODED_INT: 926}*/:
                                i = i3 + 2;
                                break;
                            case ECI_CHARSET /*{ENCODED_INT: 927}*/:
                                int i4 = i3 + 1;
                                Charset forName = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i3]).name());
                                i = i4;
                                charset = forName;
                                break;
                            case 928:
                                i = decodeMacroBlock(iArr, i3, pDF417ResultMetadata);
                                break;
                            default:
                                i = textCompaction(iArr, i3 - 1, sb);
                                break;
                        }
                }
            } else {
                sb.append((char) iArr[i3]);
                i = i3 + 1;
            }
            if (i < iArr.length) {
                i2 = iArr[i];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() != 0) {
            DecoderResult decoderResult = new DecoderResult(null, sb.toString(), null, str);
            decoderResult.setOther(pDF417ResultMetadata);
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = i;
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int textCompaction = textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            if (iArr[textCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                int i4 = textCompaction + 1;
                int[] iArr3 = new int[(iArr[0] - i4)];
                boolean z = false;
                int i5 = 0;
                while (i4 < iArr[0] && !z) {
                    int i6 = i4 + 1;
                    int i7 = iArr[i4];
                    if (i7 < TEXT_COMPACTION_MODE_LATCH) {
                        iArr3[i5] = i7;
                        i4 = i6;
                        i5++;
                    } else if (i7 == MACRO_PDF417_TERMINATOR) {
                        pDF417ResultMetadata.setLastSegment(true);
                        i4 = i6 + 1;
                        z = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
                return i4;
            } else if (iArr[textCompaction] != MACRO_PDF417_TERMINATOR) {
                return textCompaction;
            } else {
                pDF417ResultMetadata.setLastSegment(true);
                return textCompaction + 1;
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[((iArr[0] - i) * 2)];
        int[] iArr3 = new int[((iArr[0] - i) * 2)];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
                i = i3;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case TEXT_COMPACTION_MODE_LATCH /*{ENCODED_INT: 900}*/:
                            iArr2[i2] = TEXT_COMPACTION_MODE_LATCH;
                            i2++;
                            i = i3;
                            break;
                        default:
                            switch (i4) {
                                case MACRO_PDF417_TERMINATOR /*{ENCODED_INT: 922}*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*{ENCODED_INT: 923}*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*{ENCODED_INT: 924}*/:
                                    break;
                                default:
                                    i = i3;
                                    break;
                            }
                        case BYTE_COMPACTION_MODE_LATCH /*{ENCODED_INT: 901}*/:
                        case NUMERIC_COMPACTION_MODE_LATCH /*{ENCODED_INT: 902}*/:
                            i = i3 - 1;
                            z = true;
                            break;
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        char c;
        Mode mode = Mode.ALPHA;
        Mode mode2 = Mode.ALPHA;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            switch (mode) {
                case ALPHA:
                    if (i3 >= 26) {
                        if (i3 != 26) {
                            if (i3 != 27) {
                                if (i3 != 28) {
                                    if (i3 == 29) {
                                        c = 0;
                                        mode2 = mode;
                                        mode = Mode.PUNCT_SHIFT;
                                        break;
                                    } else {
                                        if (i3 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                                                mode = Mode.ALPHA;
                                                c = 0;
                                                break;
                                            }
                                        } else {
                                            sb.append((char) iArr2[i2]);
                                        }
                                        c = 0;
                                        break;
                                    }
                                } else {
                                    mode = Mode.MIXED;
                                    c = 0;
                                    break;
                                }
                            } else {
                                mode = Mode.LOWER;
                                c = 0;
                                break;
                            }
                        } else {
                            c = ' ';
                            break;
                        }
                    } else {
                        c = (char) (i3 + 65);
                        break;
                    }
                case LOWER:
                    if (i3 >= 26) {
                        if (i3 != 26) {
                            if (i3 != 27) {
                                if (i3 != 28) {
                                    if (i3 == 29) {
                                        c = 0;
                                        mode2 = mode;
                                        mode = Mode.PUNCT_SHIFT;
                                        break;
                                    } else {
                                        if (i3 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                            if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                                                mode = Mode.ALPHA;
                                                c = 0;
                                                break;
                                            }
                                        } else {
                                            sb.append((char) iArr2[i2]);
                                        }
                                        c = 0;
                                        break;
                                    }
                                } else {
                                    mode = Mode.MIXED;
                                    c = 0;
                                    break;
                                }
                            } else {
                                c = 0;
                                mode2 = mode;
                                mode = Mode.ALPHA_SHIFT;
                                break;
                            }
                        } else {
                            c = ' ';
                            break;
                        }
                    } else {
                        c = (char) (i3 + 97);
                        break;
                    }
                case MIXED:
                    if (i3 >= 25) {
                        if (i3 != 25) {
                            if (i3 != 26) {
                                if (i3 != 27) {
                                    if (i3 != 28) {
                                        if (i3 == 29) {
                                            c = 0;
                                            mode2 = mode;
                                            mode = Mode.PUNCT_SHIFT;
                                            break;
                                        } else {
                                            if (i3 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                                if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                                                    mode = Mode.ALPHA;
                                                    c = 0;
                                                    break;
                                                }
                                            } else {
                                                sb.append((char) iArr2[i2]);
                                            }
                                            c = 0;
                                            break;
                                        }
                                    } else {
                                        mode = Mode.ALPHA;
                                        c = 0;
                                        break;
                                    }
                                } else {
                                    mode = Mode.LOWER;
                                    c = 0;
                                    break;
                                }
                            } else {
                                c = ' ';
                                break;
                            }
                        } else {
                            mode = Mode.PUNCT;
                            c = 0;
                            break;
                        }
                    } else {
                        c = MIXED_CHARS[i3];
                        break;
                    }
                case PUNCT:
                    if (i3 >= 29) {
                        if (i3 == 29) {
                            mode = Mode.ALPHA;
                            c = 0;
                            break;
                        } else {
                            if (i3 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                                    mode = Mode.ALPHA;
                                    c = 0;
                                    break;
                                }
                            } else {
                                sb.append((char) iArr2[i2]);
                            }
                            c = 0;
                            break;
                        }
                    } else {
                        c = PUNCT_CHARS[i3];
                        break;
                    }
                case ALPHA_SHIFT:
                    if (i3 >= 26) {
                        if (i3 == 26) {
                            mode = mode2;
                            c = ' ';
                            break;
                        } else {
                            if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                                mode = Mode.ALPHA;
                                c = 0;
                                break;
                            }
                            mode = mode2;
                            c = 0;
                            break;
                        }
                    } else {
                        c = (char) (i3 + 65);
                        mode = mode2;
                        break;
                    }
                case PUNCT_SHIFT:
                    if (i3 >= 29) {
                        if (i3 == 29) {
                            mode = Mode.ALPHA;
                            c = 0;
                            break;
                        } else {
                            if (i3 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                if (i3 == TEXT_COMPACTION_MODE_LATCH) {
                                    mode = Mode.ALPHA;
                                    c = 0;
                                    break;
                                }
                            } else {
                                sb.append((char) iArr2[i2]);
                            }
                            mode = mode2;
                            c = 0;
                            break;
                        }
                    } else {
                        c = PUNCT_CHARS[i3];
                        mode = mode2;
                        break;
                    }
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i5 = MACRO_PDF417_TERMINATOR;
        int i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        int i7 = 928;
        long j = 900;
        if (i == BYTE_COMPACTION_MODE_LATCH) {
            int[] iArr2 = new int[6];
            i3 = i2 + 1;
            int i8 = iArr[i2];
            boolean z = false;
            int i9 = 0;
            long j2 = 0;
            while (i3 < iArr[0] && !z) {
                int i10 = i9 + 1;
                iArr2[i9] = i8;
                j2 = (j2 * j) + ((long) i8);
                int i11 = i3 + 1;
                i8 = iArr[i3];
                if (i8 == TEXT_COMPACTION_MODE_LATCH || i8 == BYTE_COMPACTION_MODE_LATCH || i8 == NUMERIC_COMPACTION_MODE_LATCH || i8 == BYTE_COMPACTION_MODE_LATCH_6 || i8 == 928 || i8 == i6 || i8 == i5) {
                    i3 = i11 - 1;
                    i9 = i10;
                    i5 = MACRO_PDF417_TERMINATOR;
                    i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    j = 900;
                    z = true;
                } else if (i10 % 5 != 0 || i10 <= 0) {
                    i3 = i11;
                    i9 = i10;
                    i5 = MACRO_PDF417_TERMINATOR;
                    i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    j = 900;
                } else {
                    int i12 = 0;
                    while (i12 < 6) {
                        byteArrayOutputStream.write((byte) ((int) (j2 >> ((5 - i12) * 8))));
                        i12++;
                        i5 = MACRO_PDF417_TERMINATOR;
                        i6 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    }
                    i3 = i11;
                    j = 900;
                    i9 = 0;
                    j2 = 0;
                }
            }
            if (i3 != iArr[0] || i8 >= TEXT_COMPACTION_MODE_LATCH) {
                i4 = i9;
            } else {
                i4 = i9 + 1;
                iArr2[i9] = i8;
            }
            for (int i13 = 0; i13 < i4; i13++) {
                byteArrayOutputStream.write((byte) iArr2[i13]);
            }
        } else if (i == BYTE_COMPACTION_MODE_LATCH_6) {
            int i14 = i2;
            int i15 = 0;
            long j3 = 0;
            boolean z2 = false;
            while (i14 < iArr[0] && !z2) {
                int i16 = i14 + 1;
                int i17 = iArr[i14];
                if (i17 < TEXT_COMPACTION_MODE_LATCH) {
                    i15++;
                    j3 = (j3 * 900) + ((long) i17);
                    i14 = i16;
                } else {
                    if (i17 != TEXT_COMPACTION_MODE_LATCH && i17 != BYTE_COMPACTION_MODE_LATCH && i17 != NUMERIC_COMPACTION_MODE_LATCH && i17 != BYTE_COMPACTION_MODE_LATCH_6 && i17 != i7) {
                        if (i17 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                            if (i17 != MACRO_PDF417_TERMINATOR) {
                                i14 = i16;
                            }
                            i14 = i16 - 1;
                            z2 = true;
                        }
                    }
                    i14 = i16 - 1;
                    z2 = true;
                }
                if (i15 % 5 == 0 && i15 > 0) {
                    int i18 = 0;
                    for (int i19 = 6; i18 < i19; i19 = 6) {
                        byteArrayOutputStream.write((byte) ((int) (j3 >> ((5 - i18) * 8))));
                        i18++;
                    }
                    i15 = 0;
                    j3 = 0;
                }
                i7 = 928;
            }
            i3 = i14;
        } else {
            i3 = i2;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == TEXT_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == 928 || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == NUMERIC_COMPACTION_MODE_LATCH || z) && i2 > 0) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
