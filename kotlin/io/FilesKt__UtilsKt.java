package kotlin.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 8}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: Utils.kt */
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    @NotNull
    public static /* bridge */ /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempDir(str, str2, file);
    }

    @NotNull
    public static final File createTempDir(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        createTempFile.delete();
        if (createTempFile.mkdir()) {
            Intrinsics.checkExpressionValueIsNotNull(createTempFile, "dir");
            return createTempFile;
        }
        throw new IOException("Unable to create temporary directory " + createTempFile + '.');
    }

    @NotNull
    public static /* bridge */ /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempFile(str, str2, file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.checkParameterIsNotNull(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        Intrinsics.checkExpressionValueIsNotNull(createTempFile, "File.createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    @NotNull
    public static final String getExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return StringsKt.substringAfterLast(file.getName(), '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        if (File.separatorChar != '/') {
            return StringsKt.replace$default(file.getPath(), File.separatorChar, '/', false, 4, (Object) null);
        }
        String path = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        return path;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        return StringsKt.substringBeforeLast$default(file.getName(), ".", (String) null, 2, (Object) null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return relativeStringOrNull$FilesKt__UtilsKt;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + '.');
    }

    @NotNull
    public static final File relativeTo(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        return new File(FilesKt.toRelativeString(file, file2));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        return relativeStringOrNull$FilesKt__UtilsKt != null ? new File(relativeStringOrNull$FilesKt__UtilsKt) : file;
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return new File(relativeStringOrNull$FilesKt__UtilsKt);
        }
        return null;
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(@NotNull File file, File file2) {
        FilePathComponents normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(FilesKt.toComponents(file));
        FilePathComponents normalize$FilesKt__UtilsKt2 = normalize$FilesKt__UtilsKt(FilesKt.toComponents(file2));
        if (!Intrinsics.areEqual(normalize$FilesKt__UtilsKt.getRoot(), normalize$FilesKt__UtilsKt2.getRoot())) {
            return null;
        }
        int size = normalize$FilesKt__UtilsKt2.getSize();
        int size2 = normalize$FilesKt__UtilsKt.getSize();
        int i = 0;
        int min = Math.min(size2, size);
        while (i < min && Intrinsics.areEqual(normalize$FilesKt__UtilsKt.getSegments().get(i), normalize$FilesKt__UtilsKt2.getSegments().get(i))) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = size - 1;
        if (i2 >= i) {
            while (!Intrinsics.areEqual(normalize$FilesKt__UtilsKt2.getSegments().get(i2).getName(), "..")) {
                sb.append("..");
                if (i2 != i) {
                    sb.append(File.separatorChar);
                }
                if (i2 != i) {
                    i2--;
                }
            }
            return null;
        }
        if (i < size2) {
            if (i < size) {
                sb.append(File.separatorChar);
            }
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            CollectionsKt.joinTo$default(CollectionsKt.drop(normalize$FilesKt__UtilsKt.getSegments(), i), sb, str, null, null, 0, null, null, 124, null);
        }
        return sb.toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return FilesKt.copyTo(file, file2, z, i);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:31|32|33|36|37|38) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:43|44|45|48|49|50) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x007e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:48:0x0091 */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0096  */
    @NotNull
    public static final File copyTo(@NotNull File file, @NotNull File file2, boolean z, int i) {
        Throwable th;
        boolean z2;
        Throwable th2;
        boolean z3;
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "target");
        if (file.exists()) {
            boolean z4 = false;
            if (file2.exists()) {
                if (z && file2.delete()) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z3) {
                    throw new FileAlreadyExistsException(file, file2, "The destination file already exists.");
                }
            }
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileInputStream fileInputStream2 = fileInputStream;
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        ByteStreamsKt.copyTo(fileInputStream2, fileOutputStream, i);
                        fileOutputStream.close();
                        fileInputStream.close();
                    } catch (Exception e) {
                        fileOutputStream.close();
                        throw e;
                    } catch (Throwable th3) {
                        th2 = th3;
                        z2 = true;
                        if (!z2) {
                        }
                        throw th2;
                    }
                } catch (Exception e2) {
                    fileInputStream.close();
                    throw e2;
                } catch (Throwable th4) {
                    th = th4;
                    z4 = true;
                    if (!z4) {
                    }
                    throw th;
                }
            } else if (!file2.mkdirs()) {
                throw new FileSystemException(file, file2, "Failed to create target directory.");
            }
            return file2;
        }
        throw new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
    }

    public static /* bridge */ /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function2 = FilesKt__UtilsKt$copyRecursively$1.INSTANCE;
        }
        return FilesKt.copyRecursively(file, file2, z, function2);
    }

    public static final boolean copyRecursively(@NotNull File file, @NotNull File file2, boolean z, @NotNull Function2<? super File, ? super IOException, ? extends OnErrorAction> function2) {
        boolean z2;
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "target");
        Intrinsics.checkParameterIsNotNull(function2, "onError");
        if (!file.exists()) {
            return !Intrinsics.areEqual((OnErrorAction) function2.invoke(file, new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null)), OnErrorAction.TERMINATE);
        }
        try {
            Iterator<File> it = FilesKt.walkTopDown(file).onFail(new FilesKt__UtilsKt$copyRecursively$2(function2)).iterator();
            while (it.hasNext()) {
                File next = it.next();
                if (next.exists()) {
                    File file3 = new File(file2, FilesKt.toRelativeString(next, file));
                    if (file3.exists() && (!next.isDirectory() || !file3.isDirectory())) {
                        if (!z) {
                            z2 = true;
                        } else if (file3.isDirectory()) {
                            z2 = !FilesKt.deleteRecursively(file3);
                        } else {
                            z2 = !file3.delete();
                        }
                        if (z2) {
                            if (Intrinsics.areEqual((OnErrorAction) function2.invoke(file3, new FileAlreadyExistsException(next, file3, "The destination file already exists.")), OnErrorAction.TERMINATE)) {
                                return false;
                            }
                        }
                    }
                    if (next.isDirectory()) {
                        file3.mkdirs();
                    } else if (FilesKt.copyTo$default(next, file3, z, 0, 4, null).length() != next.length() && Intrinsics.areEqual((OnErrorAction) function2.invoke(next, new IOException("Source file wasn't copied completely, length of destination file differs.")), OnErrorAction.TERMINATE)) {
                        return false;
                    }
                } else if (Intrinsics.areEqual((OnErrorAction) function2.invoke(next, new NoSuchFileException(next, null, "The source file doesn't exist.", 2, null)), OnErrorAction.TERMINATE)) {
                    return false;
                }
            }
            return true;
        } catch (TerminateException unused) {
            return false;
        }
    }

    public static final boolean deleteRecursively(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        boolean z = true;
        for (File file2 : FilesKt.walkBottomUp(file)) {
            z = (file2.delete() || !file2.exists()) && z;
        }
        return z;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        FilePathComponents components = FilesKt.toComponents(file);
        FilePathComponents components2 = FilesKt.toComponents(file2);
        if (!(!Intrinsics.areEqual(components.getRoot(), components2.getRoot())) && components.getSize() >= components2.getSize()) {
            return components.getSegments().subList(0, components2.getSize()).equals(components2.getSegments());
        }
        return false;
    }

    public static final boolean startsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return FilesKt.startsWith(file, new File(str));
    }

    public static final boolean endsWith(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "other");
        FilePathComponents components = FilesKt.toComponents(file);
        FilePathComponents components2 = FilesKt.toComponents(file2);
        if (components2.isRooted()) {
            return Intrinsics.areEqual(file, file2);
        }
        int size = components.getSize() - components2.getSize();
        if (size < 0) {
            return false;
        }
        return components.getSegments().subList(size, components.getSize()).equals(components2.getSegments());
    }

    public static final boolean endsWith(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "other");
        return FilesKt.endsWith(file, new File(str));
    }

    @NotNull
    public static final File normalize(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        FilePathComponents components = FilesKt.toComponents(file);
        File root = components.getRoot();
        List<File> normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(components.getSegments());
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        return FilesKt.resolve(root, CollectionsKt.joinToString$default(normalize$FilesKt__UtilsKt, str, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(@NotNull FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.getRoot(), normalize$FilesKt__UtilsKt(filePathComponents.getSegments()));
    }

    private static final List<File> normalize$FilesKt__UtilsKt(@NotNull List<? extends File> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (File file : list) {
            String name = file.getName();
            if (name != null) {
                int hashCode = name.hashCode();
                if (hashCode != 46) {
                    if (hashCode == 1472 && name.equals("..")) {
                        if (arrayList.isEmpty() || !(!Intrinsics.areEqual(((File) CollectionsKt.last((List) arrayList)).getName(), ".."))) {
                            arrayList.add(file);
                        } else {
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                } else if (name.equals(".")) {
                }
            }
            arrayList.add(file);
        }
        return arrayList;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "relative");
        if (FilesKt.isRooted(file2)) {
            return file2;
        }
        String file3 = file.toString();
        String str = file3;
        if ((str.length() == 0) || StringsKt.endsWith$default((CharSequence) str, File.separatorChar, false, 2, (Object) null)) {
            return new File(file3 + file2);
        }
        return new File(file3 + File.separatorChar + file2);
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "relative");
        return FilesKt.resolve(file, new File(str));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull File file2) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(file2, "relative");
        FilePathComponents components = FilesKt.toComponents(file);
        return FilesKt.resolve(FilesKt.resolve(components.getRoot(), components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1)), file2);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(file, "$receiver");
        Intrinsics.checkParameterIsNotNull(str, "relative");
        return FilesKt.resolveSibling(file, new File(str));
    }
}
