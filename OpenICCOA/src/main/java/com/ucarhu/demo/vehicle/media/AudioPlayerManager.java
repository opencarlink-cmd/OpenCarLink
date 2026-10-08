package com.ucarhu.demo.vehicle.media;

public class AudioPlayerManager {

    private static final java.lang.String f828m = "AudioPlayerMgr";

    private static final int f829n = 0;

    private final android.media.AudioManager f835f;

    private android.media.AudioFocusRequest f836g;

    private com.ucar.vehiclesdk.UCarCommon.AudioType f837h;

    private android.media.AudioManager.OnAudioFocusChangeListener f840k;

    private final com.ucarhu.demo.vehicle.audio.UCarAudioManager.c f841l;

    private final java.util.Map<com.ucar.vehiclesdk.UCarCommon.AudioType, com.ucarhu.demo.vehicle.media.StreamAudioPlayer> f830a = new java.util.HashMap();

    private final java.util.Map<com.ucar.vehiclesdk.UCarCommon.AudioType, com.ucarhu.demo.vehicle.audio.UCarAudioManager.b> f831b = new java.util.HashMap();

    private final java.util.Map<com.ucar.vehiclesdk.UCarCommon.AudioType, com.ucar.vehiclesdk.UCarCommon.AudioAttributes> f832c = new java.util.HashMap();

    private final java.util.Queue<com.ucar.vehiclesdk.UCarCommon.AudioType> f833d = new java.util.LinkedList();

    private boolean f834e = true;

    private int f838i = -1;

    private int f839j = 0;

    public static class a {

        public static final int[] f842a;

        static {
            com.ucarhu.demo.vehicle.audio.UCarAudioManager.d.values();
            int[] iArr = new int[4];
            f842a = iArr;
            try {
                com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVar = com.ucarhu.demo.vehicle.audio.UCarAudioManager.d.START_PLAYER;
                iArr[0] = 1;
            } catch (java.lang.NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = f842a;
                com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVar2 = com.ucarhu.demo.vehicle.audio.UCarAudioManager.d.RESUME_PLAYER;
                iArr2[3] = 2;
            } catch (java.lang.NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = f842a;
                com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVar3 = com.ucarhu.demo.vehicle.audio.UCarAudioManager.d.PAUSE_PLAYER;
                iArr3[2] = 3;
            } catch (java.lang.NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = f842a;
                com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVar4 = com.ucarhu.demo.vehicle.audio.UCarAudioManager.d.STOP_PLAYER;
                iArr4[1] = 4;
            } catch (java.lang.NoSuchFieldError unused4) {
            }
        }
    }

    public AudioPlayerManager(android.content.Context context, com.ucarhu.demo.vehicle.audio.UCarAudioManager.c cVar) {
        this.f835f = (android.media.AudioManager) context.getSystemService("audio");
        com.ucarhu.demo.vehicle.media.AudioSessionProvider.getInstance(context);
        this.f841l = cVar;
    }

    public void m965a(int i) {
        java.lang.String str = f828m;
        java.lang.String str2 = null;
        com.ucarhu.demo.logging.EasyLogger.info(f828m, "onAudioFocusChange, new focus: " + i);
        synchronized (this) {
            if (i != -3) {
                if (i == -2) {
                    com.ucarhu.demo.logging.EasyLogger.info(f828m, "onAudioFocus loss transient, need pause");
                    m970i(true);
                } else if (i == -1) {
                    com.ucarhu.demo.logging.EasyLogger.info(f828m, "onAudioFocus loss, need stop");
                    m970i(false);
                } else if (i != 1) {
                    str = f828m;
                    str2 = "onAudioFocusChange:" + i;
                } else {
                    com.ucarhu.demo.logging.EasyLogger.info(f828m, "onAudioFocus gain, now play");
                    m974o();
                }
                this.f838i = i;
            } else {
                str = f828m;
                str2 = "onAudioFocus loss transient can duck, need pause or lower the volume";
            }
            if (str2 != null) {
                com.ucarhu.demo.logging.EasyLogger.info(str, str2);
            }
            this.f838i = i;
        }
    }

    private void m966c(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, int i, int i2) {
        com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135gM972l = m972l(audioType);
        if (c0135gM972l != null) {
            c0135gM972l.updateAudioPlayParams(i, i2);
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.warn(f828m, "Player is not created: " + audioType);
    }

    public static void m967d(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135g) throws java.lang.IllegalStateException {
        if (c0135g != null) {
            c0135g.pause();
        }
    }

    private void m968e(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, com.ucar.vehiclesdk.UCarCommon.AudioAttributes audioAttributes) throws java.lang.IllegalStateException {
        int iRequestAudioFocus;
        java.lang.StringBuilder sb;
        java.lang.String str;
        com.ucarhu.demo.logging.EasyLogger.info(f828m, "requestAudioFocus, type: " + audioType);
        if (this.f838i == 1 && this.f839j < audioAttributes.getFocusGain()) {
            m971k(this.f837h);
        }
        if (this.f838i != 1) {
            com.ucarhu.demo.vehicle.audio.UCarAudioManager.c cVar = this.f841l;
            if (cVar != null) {
                cVar.onRequestAudioFocus(audioType, audioAttributes.getFocusGain());
            }
            if (this.f840k == null) {
                this.f840k = new android.media.AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public final void onAudioFocusChange(int i) {
                        AudioPlayerManager.this.m965a(i);
                    }
                };
            }
            if (android.os.Build.VERSION.SDK_INT >= 26) {
                android.media.AudioFocusRequest audioFocusRequestBuild = new android.media.AudioFocusRequest.Builder(audioAttributes.getFocusGain()).setAudioAttributes(audioAttributes.getAudioAttributes()).setOnAudioFocusChangeListener(this.f840k).setAcceptsDelayedFocusGain(false).setWillPauseWhenDucked(true).build();
                this.f836g = audioFocusRequestBuild;
                iRequestAudioFocus = this.f835f.requestAudioFocus(audioFocusRequestBuild);
            } else {
                iRequestAudioFocus = this.f835f.requestAudioFocus(this.f840k, audioAttributes.getStreamType(), audioAttributes.getFocusGain());
            }
        } else {
            iRequestAudioFocus = -1;
        }
        int focusGain = audioAttributes.getFocusGain();
        if (iRequestAudioFocus == 0) {
            com.ucarhu.demo.logging.EasyLogger.error(f828m, "request audio focus failed, audioType: " + audioType + " request gain: " + focusGain + " current gain: " + this.f839j);
        } else if (iRequestAudioFocus == 1 || this.f838i == 1) {
            if (this.f838i == 1) {
                sb = new java.lang.StringBuilder();
                str = "request audio focus ignored, audioType: ";
            } else {
                this.f839j = audioAttributes.getFocusGain();
                this.f838i = 1;
                this.f837h = audioType;
                sb = new java.lang.StringBuilder();
                str = "request audio focus succeed, audioType: ";
            }
            sb.append(str);
            sb.append(audioType);
            sb.append(" request gain: ");
            sb.append(focusGain);
            sb.append(" current gain: ");
            sb.append(this.f839j);
            com.ucarhu.demo.logging.EasyLogger.info(f828m, sb.toString());
            m974o();
        } else {
            com.ucarhu.demo.logging.EasyLogger.warn(f828m, "request audio dose nothing, audioType: " + audioType + " request gain: " + focusGain + " current gain: " + this.f839j);
        }
        this.f833d.remove(audioType);
    }

    private synchronized void m969g(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, com.ucar.vehiclesdk.UCarCommon.AudioAttributes audioAttributes) {
        if (this.f830a.get(audioType) == null) {
            com.ucarhu.demo.logging.EasyLogger.debug(f828m, "create audio player!, type:" + audioType);
            if (audioAttributes == null) {
                audioAttributes = new com.ucar.vehiclesdk.UCarCommon.AudioAttributes(new android.media.AudioAttributes.Builder().setUsage(1).setContentType(2).build(), 1, 3);
            }
            try {
                com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135g = new com.ucarhu.demo.vehicle.media.StreamAudioPlayer(audioAttributes.getAudioAttributes(), audioFormat);
                this.f830a.put(audioType, c0135g);
                com.ucarhu.demo.vehicle.audio.UCarAudioManager.b bVar = this.f831b.get(audioType);
                if (bVar != null) {
                    m966c(audioType, bVar.getBufferingCount(), bVar.getSpeedAdjustStep());
                }
                c0135g.startPlayback();
                this.f832c.put(audioType, audioAttributes);
                this.f833d.add(audioType);
            } catch (java.lang.Exception e2) {
                com.ucarhu.demo.logging.EasyLogger.errorWithThrowable(f828m, "create audio player failed.", e2);
            }
        }
    }

    private void m970i(boolean z) {
        if (!z) {
            com.ucar.vehiclesdk.UCarAdapter uCarAdapter = com.ucar.vehiclesdk.UCarAdapter.getInstance();
            com.ucar.vehiclesdk.UCarCommon.KeyEventActionType keyEventActionType = com.ucar.vehiclesdk.UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_DOWN;
            com.ucar.vehiclesdk.UCarCommon.KeyCodeType keyCodeType = com.ucar.vehiclesdk.UCarCommon.KeyCodeType.KEY_CODE_MEDIA_PAUSE;
            uCarAdapter.sendKeyEvent(keyEventActionType, keyCodeType, 0);
            com.ucar.vehiclesdk.UCarAdapter.getInstance().sendKeyEvent(com.ucar.vehiclesdk.UCarCommon.KeyEventActionType.KEY_EVENT_ACTION_UP, keyCodeType, 0);
        }
        com.ucar.vehiclesdk.UCarCommon.AudioType audioType = this.f837h;
        if (audioType != null) {
            m979u(audioType);
        }
        this.f830a.forEach(new java.util.function.BiConsumer() {
            @Override
            public final void accept(java.lang.Object obj, java.lang.Object obj2){
                com.ucarhu.demo.vehicle.media.AudioPlayerManager.m967d((com.ucar.vehiclesdk.UCarCommon.AudioType) obj, (com.ucarhu.demo.vehicle.media.StreamAudioPlayer) obj2);
            }
        });
    }

    private boolean m971k(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
        int iAbandonAudioFocus;
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            android.media.AudioFocusRequest audioFocusRequest = this.f836g;
            iAbandonAudioFocus = audioFocusRequest != null ? this.f835f.abandonAudioFocusRequest(audioFocusRequest) : 0;
        } else {
            iAbandonAudioFocus = this.f835f.abandonAudioFocus(this.f840k);
        }
        if (iAbandonAudioFocus != 1) {
            com.ucarhu.demo.logging.EasyLogger.error(f828m, "abandon audio focus failed, audioType: " + audioType);
            return false;
        }
        m979u(audioType);
        this.f836g = null;
        this.f838i = -1;
        this.f839j = 0;
        this.f837h = null;
        this.f840k = null;
        com.ucarhu.demo.logging.EasyLogger.info(f828m, "abandon audio focus succeed, audioType: " + audioType);
        return true;
    }

    private com.ucarhu.demo.vehicle.media.StreamAudioPlayer m972l(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
        return this.f830a.get(audioType);
    }

    public static void m973n(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135g) {
        if (c0135g != null) {
            c0135g.stop();
            c0135g.release();
        }
    }

    private void m974o() throws java.lang.IllegalStateException {
        com.ucar.vehiclesdk.UCarCommon.AudioType audioType = this.f837h;
        if (audioType != null) {
            int i = (audioType == com.ucar.vehiclesdk.UCarCommon.AudioType.STREAM_IP_CALL || audioType == com.ucar.vehiclesdk.UCarCommon.AudioType.STREAM_MODEM_CALL) ? 3 : 0;
            if (this.f835f.getMode() != i) {
                this.f835f.setMode(i);
            }
            com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135gM972l = m972l(this.f837h);
            if (c0135gM972l != null) {
                c0135gM972l.resume();
            }
        }
    }

    private synchronized void m975p(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
        com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135g = this.f830a.get(audioType);
        if (c0135g != null) {
            c0135g.pause();
            com.ucarhu.demo.logging.EasyLogger.info(f828m, "pausePlayer, audioType: " + audioType);
        }
    }

    private synchronized void m976r(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
        com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135g = this.f830a.get(audioType);
        if (c0135g != null) {
            c0135g.resume();
            com.ucarhu.demo.logging.EasyLogger.info(f828m, "resumePlayer, audioType: " + audioType);
        }
    }

    private synchronized void m977s(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
        com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135g = this.f830a.get(audioType);
        if (c0135g != null) {
            c0135g.stop();
            c0135g.release();
            this.f830a.remove(audioType);
            this.f832c.remove(audioType);
            if (this.f837h == audioType) {
                this.f834e = true;
                m971k(audioType);
            }
            this.f833d.remove(audioType);
        }
        com.ucarhu.demo.logging.EasyLogger.info(f828m, "stopPlayer, audioType: " + audioType);
    }

    private synchronized boolean m978t(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
        if (this.f837h != audioType) {
            return false;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f828m, "we will abandon audio focus");
        boolean zM971k = m971k(audioType);
        if (zM971k) {
            m975p(audioType);
        }
        return zM971k;
    }

    private void m979u(com.ucar.vehiclesdk.UCarCommon.AudioType audioType) {
        if ((audioType == com.ucar.vehiclesdk.UCarCommon.AudioType.STREAM_IP_CALL || audioType == com.ucar.vehiclesdk.UCarCommon.AudioType.STREAM_MODEM_CALL) && this.f835f.getMode() == 3) {
            this.f835f.setMode(0);
        }
    }

    public synchronized void m983b(com.ucarhu.demo.vehicle.audio.UCarAudioManager.b bVar) {
        if (bVar.getAudioType() == com.ucar.vehiclesdk.UCarCommon.AudioType.STREAM_UNDEFINED) {
            java.util.Iterator<java.util.Map.Entry<com.ucar.vehiclesdk.UCarCommon.AudioType, com.ucarhu.demo.vehicle.media.StreamAudioPlayer>> it = this.f830a.entrySet().iterator();
            while (it.hasNext()) {
                m966c(it.next().getKey(), bVar.getBufferingCount(), bVar.getSpeedAdjustStep());
            }
        } else {
            m966c(bVar.getAudioType(), bVar.getBufferingCount(), bVar.getSpeedAdjustStep());
        }
        this.f831b.put(bVar.getAudioType(), bVar);
    }

    public void manageAudioPlayerState(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, com.ucar.vehiclesdk.UCarCommon.AudioFormat audioFormat, com.ucarhu.demo.vehicle.audio.UCarAudioManager.d dVar, com.ucar.vehiclesdk.UCarCommon.AudioAttributes audioAttributes) {
        if (audioFormat == null) {
            com.ucarhu.demo.logging.EasyLogger.error(f828m, "format null");
            return;
        }
        com.ucarhu.demo.logging.EasyLogger.info(f828m, "manage audio player!, type:" + audioType + " format " + audioFormat);
        int iOrdinal = dVar.ordinal();
        if (iOrdinal == 0) {
            m969g(audioType, audioFormat, audioAttributes);
            return;
        }
        if (iOrdinal == 1) {
            m977s(audioType);
            return;
        }
        if (iOrdinal == 2) {
            m975p(audioType);
        } else if (iOrdinal != 3) {
            com.ucarhu.demo.logging.EasyLogger.error(f828m, "player state is unknown!");
        } else {
            m976r(audioType);
        }
    }

    public synchronized void enqueueAudioData(com.ucar.vehiclesdk.UCarCommon.AudioType audioType, java.nio.ByteBuffer byteBuffer) {
        java.lang.String str = null;
        java.lang.String str2 = f828m;
        com.ucar.vehiclesdk.UCarCommon.AudioAttributes audioAttributes;
        com.ucarhu.demo.vehicle.media.StreamAudioPlayer c0135gM972l = m972l(audioType);
        if (c0135gM972l != null) {
            if ((this.f834e || this.f833d.peek() == audioType) && (audioAttributes = this.f832c.get(audioType)) != null) {
                m968e(audioType, audioAttributes);
                this.f834e = false;
            }
            if (this.f838i == 1) {
                c0135gM972l.enqueueAudio(byteBuffer);
            } else {
                str = "no focus, drop data, type = " + audioType;
                str2 = f828m;
            }
        } else {
            str = "no player, drop data, type = " + audioType;
            str2 = f828m;
        }
        if (str != null) {
            com.ucarhu.demo.logging.EasyLogger.debug(str2, str);
        }
    }

    public synchronized boolean abandonCurrentAudioFocus() {
        this.f834e = false;
        if (this.f838i == -1) {
            return true;
        }
        com.ucar.vehiclesdk.UCarCommon.AudioType audioType = this.f837h;
        if (audioType == null) {
            return false;
        }
        return m978t(audioType);
    }

    public synchronized void m987m() {
        this.f834e = true;
    }

    public synchronized void m988q() {
        com.ucarhu.demo.logging.EasyLogger.info(f828m, "clearAudioPlayers");
        this.f830a.forEach(new java.util.function.BiConsumer() {
            @Override
            public final void accept(java.lang.Object obj, java.lang.Object obj2){
                com.ucarhu.demo.vehicle.media.AudioPlayerManager.m973n((com.ucar.vehiclesdk.UCarCommon.AudioType) obj, (com.ucarhu.demo.vehicle.media.StreamAudioPlayer) obj2);
            }
        });
        this.f830a.clear();
        this.f832c.clear();
        this.f833d.clear();
        this.f837h = null;
        this.f836g = null;
        this.f834e = true;
    }
}
