package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Map;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int length = iArr.length;
        int i = nextSet;
        boolean z = false;
        int i2 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                int i3 = length - 1;
                if (i2 == i3) {
                    float f = MAX_AVG_VARIANCE;
                    int i4 = -1;
                    for (int i5 = 103; i5 <= 105; i5++) {
                        float patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i5], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < f) {
                            i4 = i5;
                            f = patternMatchVariance;
                        }
                    }
                    if (i4 < 0 || !bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                        i += iArr[0] + iArr[1];
                        int i6 = length - 2;
                        System.arraycopy(iArr, 2, iArr, 0, i6);
                        iArr[i6] = 0;
                        iArr[i3] = 0;
                        i2--;
                    } else {
                        return new int[]{i, nextSet, i4};
                    }
                } else {
                    i2++;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i3 >= iArr2.length) {
                break;
            }
            float patternMatchVariance = patternMatchVariance(iArr, iArr2[i3], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < f) {
                i2 = i3;
                f = patternMatchVariance;
            }
            i3++;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        char c;
        char c2;
        boolean z;
        boolean z2;
        char c3;
        boolean z3;
        char c4;
        boolean z4;
        boolean z5 = false;
        boolean z6 = map != null && map.containsKey(DecodeHintType.ASSUME_GS1);
        int[] findStartPattern = findStartPattern(bitArray);
        int i2 = findStartPattern[2];
        ArrayList arrayList = new ArrayList(20);
        arrayList.add(Byte.valueOf((byte) i2));
        switch (i2) {
            case 103:
                c = 'e';
                break;
            case 104:
                c = 'd';
                break;
            case 105:
                c = 'c';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        StringBuilder sb = new StringBuilder(20);
        int[] iArr = new int[6];
        int i3 = i2;
        char c5 = c;
        int i4 = 0;
        boolean z7 = false;
        boolean z8 = false;
        int i5 = 0;
        int i6 = 0;
        boolean z9 = true;
        int i7 = findStartPattern[0];
        int i8 = findStartPattern[1];
        boolean z10 = false;
        while (!z8) {
            int decodeCode = decodeCode(bitArray, iArr, i8);
            arrayList.add(Byte.valueOf((byte) decodeCode));
            if (decodeCode != 106) {
                z9 = true;
            }
            if (decodeCode != 106) {
                i6++;
                i3 += i6 * decodeCode;
            }
            int i9 = i8;
            for (int i10 : iArr) {
                i9 += i10;
            }
            switch (decodeCode) {
                case 103:
                case 104:
                case 105:
                    throw FormatException.getFormatInstance();
                default:
                    switch (c5) {
                        case 'c':
                            if (decodeCode >= 100) {
                                if (decodeCode != 106) {
                                    z9 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 100:
                                            z = z5;
                                            z2 = false;
                                            c5 = 'd';
                                            break;
                                        case 101:
                                            z = z5;
                                            z2 = false;
                                            c5 = 'e';
                                            break;
                                        case 102:
                                            if (z6) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                } else {
                                                    sb.append((char) 29);
                                                }
                                            }
                                        default:
                                            z = z5;
                                            z2 = false;
                                            break;
                                    }
                                } else {
                                    z = z5;
                                    z2 = false;
                                    z8 = true;
                                    break;
                                }
                            } else {
                                if (decodeCode < 10) {
                                    sb.append('0');
                                }
                                sb.append(decodeCode);
                            }
                            z = z5;
                            z2 = false;
                        case 'd':
                            if (decodeCode < 96) {
                                if (z5 == z7) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                                z2 = false;
                                z = false;
                                break;
                            } else {
                                if (decodeCode != 106) {
                                    z9 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 96:
                                        case 97:
                                        default:
                                            c3 = c5;
                                            z3 = z5;
                                            z2 = false;
                                            break;
                                        case 98:
                                            z3 = z5;
                                            z2 = true;
                                            c3 = 'e';
                                            break;
                                        case 99:
                                            z3 = z5;
                                            z2 = false;
                                            c3 = 'c';
                                            break;
                                        case 100:
                                            if (z7 || !z5) {
                                                if (!z7 || !z5) {
                                                    c3 = c5;
                                                    z2 = false;
                                                    z3 = true;
                                                    break;
                                                } else {
                                                    c3 = c5;
                                                    z2 = false;
                                                    z7 = false;
                                                    z3 = false;
                                                    break;
                                                }
                                            } else {
                                                c3 = c5;
                                                z2 = false;
                                                z7 = true;
                                                z3 = false;
                                                break;
                                            }
                                        case 101:
                                            z3 = z5;
                                            z2 = false;
                                            c3 = 'e';
                                            break;
                                        case 102:
                                            if (z6) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                } else {
                                                    sb.append((char) 29);
                                                }
                                            }
                                            c3 = c5;
                                            z3 = z5;
                                            z2 = false;
                                            break;
                                    }
                                } else {
                                    c3 = c5;
                                    z8 = true;
                                    z3 = z5;
                                    z2 = false;
                                }
                                z = z3;
                                c5 = c3;
                                break;
                            }
                        case 'e':
                            if (decodeCode >= 64) {
                                if (decodeCode < 96) {
                                    if (z5 == z7) {
                                        sb.append((char) (decodeCode - 64));
                                    } else {
                                        sb.append((char) (decodeCode + 64));
                                    }
                                    z2 = false;
                                    z = false;
                                    break;
                                } else {
                                    if (decodeCode != 106) {
                                        z9 = false;
                                    }
                                    if (decodeCode != 106) {
                                        switch (decodeCode) {
                                            case 96:
                                            case 97:
                                            default:
                                                c4 = c5;
                                                z4 = z5;
                                                z2 = false;
                                                break;
                                            case 98:
                                                z4 = z5;
                                                z2 = true;
                                                c4 = 'd';
                                                break;
                                            case 99:
                                                z4 = z5;
                                                z2 = false;
                                                c4 = 'c';
                                                break;
                                            case 100:
                                                z4 = z5;
                                                z2 = false;
                                                c4 = 'd';
                                                break;
                                            case 101:
                                                if (z7 || !z5) {
                                                    if (!z7 || !z5) {
                                                        c4 = c5;
                                                        z2 = false;
                                                        z4 = true;
                                                        break;
                                                    } else {
                                                        c4 = c5;
                                                        z2 = false;
                                                        z7 = false;
                                                        z4 = false;
                                                        break;
                                                    }
                                                } else {
                                                    c4 = c5;
                                                    z2 = false;
                                                    z7 = true;
                                                    z4 = false;
                                                    break;
                                                }
                                            case 102:
                                                if (z6) {
                                                    if (sb.length() == 0) {
                                                        sb.append("]C1");
                                                    } else {
                                                        sb.append((char) 29);
                                                    }
                                                }
                                                c4 = c5;
                                                z4 = z5;
                                                z2 = false;
                                                break;
                                        }
                                    } else {
                                        c4 = c5;
                                        z8 = true;
                                        z4 = z5;
                                        z2 = false;
                                    }
                                    z = z4;
                                    c5 = c4;
                                    break;
                                }
                            } else {
                                if (z5 == z7) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                                z2 = false;
                                z = false;
                                break;
                            }
                        default:
                            z = z5;
                            z2 = false;
                            break;
                    }
                    if (z10) {
                        c5 = c5 == 'e' ? 'd' : 'e';
                    }
                    z10 = z2;
                    i7 = i8;
                    z5 = z;
                    i8 = i9;
                    i5 = decodeCode;
                    i4 = i5;
            }
        }
        int i11 = i8 - i7;
        int nextUnset = bitArray.getNextUnset(i8);
        if (!bitArray.isRange(nextUnset, Math.min(bitArray.getSize(), ((nextUnset - i7) / 2) + nextUnset), false)) {
            throw NotFoundException.getNotFoundInstance();
        } else if ((i3 - (i6 * i4)) % 103 == i4) {
            int length = sb.length();
            if (length != 0) {
                if (length <= 0 || !z9) {
                    c2 = 1;
                } else if (c5 == 'c') {
                    sb.delete(length - 2, length);
                    c2 = 1;
                } else {
                    sb.delete(length - 1, length);
                    c2 = 1;
                }
                float f = ((float) (findStartPattern[c2] + findStartPattern[0])) / 2.0f;
                float f2 = ((float) i7) + (((float) i11) / 2.0f);
                int size = arrayList.size();
                byte[] bArr = new byte[size];
                for (int i12 = 0; i12 < size; i12++) {
                    bArr[i12] = ((Byte) arrayList.get(i12)).byteValue();
                }
                float f3 = (float) i;
                return new Result(sb.toString(), bArr, new ResultPoint[]{new ResultPoint(f, f3), new ResultPoint(f2, f3)}, BarcodeFormat.CODE_128);
            }
            throw NotFoundException.getNotFoundInstance();
        } else {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
