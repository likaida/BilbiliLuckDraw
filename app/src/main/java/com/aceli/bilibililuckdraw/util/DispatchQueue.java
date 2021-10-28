package com.aceli.bilibililuckdraw.util;//


import android.os.Handler;
import android.os.Looper;
import android.os.Message;


public class DispatchQueue extends Thread {
    public volatile Handler handler = null;
    private final Object handlerSyncObject = new Object();

    public DispatchQueue(String threadName) {
        this.setName(threadName);
        this.start();
    }

    private void sendMessage(Message msg, int delay) {
        if (this.handler == null) {
            try {
                Object t = this.handlerSyncObject;
                synchronized (this.handlerSyncObject) {
                    this.handlerSyncObject.wait();
                }
            } catch (Throwable var6) {
                var6.printStackTrace();
            }
        }

        if (this.handler != null) {
            if (delay <= 0) {
                this.handler.sendMessage(msg);
            } else {
                this.handler.sendMessageDelayed(msg, (long) delay);
            }
        }

    }

    public void cancelRunnable(Runnable runnable) {
        if (this.handler == null) {
            Object var2 = this.handlerSyncObject;
            synchronized (this.handlerSyncObject) {
                if (this.handler == null) {
                    try {
                        this.handlerSyncObject.wait();
                    } catch (Throwable var5) {
                        var5.printStackTrace();
                    }
                }
            }
        }

        if (this.handler != null) {
            this.handler.removeCallbacks(runnable);
        }

    }

    public void postRunnable(Runnable runnable) {
        this.postRunnable(runnable, 0L);
    }

    public void postRunnable(Runnable runnable, long delay) {
        if (this.handler == null) {
            Object var4 = this.handlerSyncObject;
            synchronized (this.handlerSyncObject) {
                if (this.handler == null) {
                    try {
                        this.handlerSyncObject.wait();
                    } catch (Throwable var7) {
                        var7.printStackTrace();
                    }
                }
            }
        }

        if (this.handler != null) {
            if (delay <= 0L) {
                this.handler.post(runnable);
            } else {
                this.handler.postDelayed(runnable, delay);
            }
        }

    }

    public void cleanupQueue() {
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages((Object) null);
        }

    }

    @Override
    public void run() {
        Looper.prepare();
        Object var1 = this.handlerSyncObject;
        synchronized (this.handlerSyncObject) {
            this.handler = new Handler();
            this.handlerSyncObject.notify();
        }

        Looper.loop();
    }
}
