package com.ucarhu.demo.sharelink.channel;

public class ShareLinkChannel extends com.ucarhu.demo.protocol.channel.socket.SocketChannel {
    public ShareLinkChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b) {
        super(enumC0057b);
    }

    public ShareLinkChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z) {
        this(enumC0057b, z, true);
    }

    public ShareLinkChannel(com.ucarhu.demo.protocol.channel.ChannelType enumC0057b, boolean z, boolean z2) {
        super(enumC0057b, z, z2);
    }

    @Override
    public void onChannelBound() {
        com.ucarhu.demo.connection.ConnectionManager.getInstance().registerAoaServerSocketIfNeeded(mo357i(), true);
    }
}
