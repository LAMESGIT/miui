package org.a.a;

import android.media.AudioTrack;

/* compiled from: AudioPlayer */
public class a {
    private long mHandle;
    private int mStatus = 0;
    private b uA = null;
    private AudioTrack ux = null;
    private long uy = 0;
    private Thread uz = null;

    public a(long j) {
        this.mHandle = j;
    }

    /* renamed from: if */
    public long m1if() {
        return this.mHandle;
    }

    public boolean a(b bVar) {
        this.uA = bVar;
        this.uy = (long) AudioTrack.getMinBufferSize(44100, 12, 2);
        if (this.uy <= 0) {
            return false;
        }
        ij();
        if (this.ux.getState() != 1) {
            return false;
        }
        this.mStatus = 1;
        if (this.uA != null) {
            this.uA.setAudioMinSize(this.mHandle, 4096);
        }
        return true;
    }

    public void ig() {
        this.mStatus = 0;
        ii();
        ik();
    }

    public void ih() {
        if (this.mStatus == 1) {
            this.mStatus = 2;
            il();
        }
    }

    public void resume() {
        if (this.mStatus == 3) {
            this.mStatus = 2;
        }
    }

    public void pause() {
        if (this.mStatus == 2) {
            this.mStatus = 3;
        }
    }

    public void ii() {
        this.mStatus = 4;
        if (this.uA != null) {
            this.uA.stopAudio(this.mHandle);
        }
        if (this.uz != null) {
            try {
                this.uz.join();
            } catch (Exception e) {
            }
        }
        this.uz = null;
    }

    private void ij() {
        ik();
        this.ux = new AudioTrack(3, 44100, 12, 2, (int) this.uy, 1);
    }

    private void ik() {
        if (this.ux != null) {
            this.ux.flush();
            if (this.ux.getPlayState() == 1) {
                this.ux.stop();
            }
            this.ux.release();
            this.ux = null;
        }
    }

    private int il() {
        if (this.mStatus != 2) {
            return -1;
        }
        if (this.uA == null) {
            return -2;
        }
        this.uz = new Thread(new Runnable() {
            public void run() {
                if (a.this.ux != null && a.this.mStatus == 2) {
                    a.this.ux.play();
                }
                while (a.this.mStatus != 4) {
                    byte[] playAudioSamples = a.this.mStatus == 3 ? null : a.this.uA.playAudioSamples(a.this.mHandle);
                    if (playAudioSamples == null || playAudioSamples.length <= 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            return;
                        }
                    } else if (a.this.ux != null) {
                        try {
                            a.this.ux.write(playAudioSamples, 0, playAudioSamples.length);
                        } catch (Exception e2) {
                        }
                    }
                }
            }
        });
        this.uz.start();
        return 0;
    }
}
