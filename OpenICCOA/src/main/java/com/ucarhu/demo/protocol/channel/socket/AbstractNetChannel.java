package com.ucarhu.demo.protocol.channel.socket;

public abstract class AbstractNetChannel implements com.ucarhu.demo.protocol.channel.NetChannel {

    private static final java.lang.String f468g = "NetChannel";

    public static final java.lang.String f469h = "127.0.0.1";

    private final com.ucarhu.demo.protocol.channel.ChannelType f470b;

    private final boolean f471c;

    public int f472d;

    private java.lang.String f473e;

    private java.lang.String f474f;

    public AbstractNetChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b) {
        this(enumC0057b, false);
    }

    public AbstractNetChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z) {
        this.f472d = 0;
        this.f473e = "127.0.0.1";
        this.f470b = enumC0057b;
        this.f471c = z;
        this.f474f = enumC0057b.name();
    }

    @Override
    public com.ucarhu.demo.protocol.channel.ChannelType mo347I() {
        return this.f470b;
    }

    public void m372K(java.lang.String str) {
        if (str == null) {
            return;
        }
        this.f473e = str;
    }

    public abstract void onChannelBound();

    public abstract void mo135O();

    @Override
    public java.lang.String mo350a() {
        return this.f474f;
    }

    @Override
    public java.lang.String mo354c() {
        return this.f473e;
    }

    @Override
    public int mo357i() {
        return this.f472d;
    }

    @Override
    public boolean mo358j() {
        return this.f471c;
    }

    public java.lang.String m373k(java.lang.String str) {
        int iMo357i;
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        java.lang.String strMo350a = mo350a();
        if (mo350a().equals(com.ucarhu.demo.protocol.channel.ChannelType.CUSTOM.name()) && (iMo357i = mo357i()) != 0) {
            strMo350a = java.lang.Integer.toString(iMo357i);
        }
        sb.append(this.f471c ? "S-" : "C-");
        sb.append(str);
        sb.append("-");
        sb.append(strMo350a);
        sb.append("-");
        sb.append(java.lang.Thread.currentThread().getId());
        return sb.toString();
    }

    public void m374n(int i) {
        if (this.f470b != com.ucarhu.demo.protocol.channel.ChannelType.CUSTOM) {
            throw new java.lang.IllegalArgumentException("Can not set " + mo350a() + " channel port to " + this.f473e);
        }
        if (i != 0 || mo358j()) {
            this.f472d = i;
            return;
        }
        throw new java.lang.IllegalArgumentException("Can not set " + mo350a() + " channel port to 0.");
    }

    @Override
    public void mo359q0() {
        try {
            close();
        } catch (java.io.IOException e2) {
            com.ucarhu.demo.protocol.ProtocolConfig.getLogger().warnWithThrowable(f468g, "Close error: " + e2.getMessage(), e2);
        }
    }

    public void m375x(java.lang.String str) {
        if (str == null) {
            return;
        }
        this.f474f = str;
    }
}
