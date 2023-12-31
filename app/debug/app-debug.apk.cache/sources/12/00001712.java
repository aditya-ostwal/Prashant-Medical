package com.squareup.picasso;

/* loaded from: classes.dex */
public interface Callback {
    void onError(Exception exc);

    void onSuccess();

    /* loaded from: classes.dex */
    public static class EmptyCallback implements Callback {
        @Override // com.squareup.picasso.Callback
        public void onSuccess() {
        }

        @Override // com.squareup.picasso.Callback
        public void onError(Exception e) {
        }
    }
}