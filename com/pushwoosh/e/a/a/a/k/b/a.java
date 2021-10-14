package com.pushwoosh.e.a.a.a.k.b;

import java.io.File;

public final class a implements b {
    private final File a;
    private final File b;
    private final File c;

    public a(String str, File file) {
        this.a = b(file, str, "values");
        this.b = b(file, str, "backup");
        this.c = b(file, str, "lock");
    }

    private File a(File file, String str, String str2) {
        return new File(new File(new File(file, "preferences"), str), str2);
    }

    private File b(File file, String str, String str2) {
        File a2 = a(file, str, str2);
        if (a2.exists() || a2.mkdirs()) {
            return a2;
        }
        throw new com.pushwoosh.e.a.a.a.j.a(String.format("Can't create preferences directory in %s", a2.getAbsolutePath()));
    }

    @Override // com.pushwoosh.e.a.a.a.k.b.b
    public File a() {
        return this.c;
    }

    @Override // com.pushwoosh.e.a.a.a.k.b.b
    public File b() {
        return this.b;
    }

    @Override // com.pushwoosh.e.a.a.a.k.b.b
    public File c() {
        return this.a;
    }
}
