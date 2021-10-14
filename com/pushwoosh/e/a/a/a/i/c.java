package com.pushwoosh.e.a.a.a.i;

public interface c {
    public static final c a = new b();

    static class a implements c {
        a() {
        }

        @Override // com.pushwoosh.e.a.a.a.i.c
        public void a(Exception exc) {
        }
    }

    static class b implements c {
        b() {
        }

        @Override // com.pushwoosh.e.a.a.a.i.c
        public void a(Exception exc) {
            exc.printStackTrace();
        }
    }

    static {
        new a();
    }

    void a(Exception exc);
}
