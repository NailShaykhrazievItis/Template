// ISongAidlInterface.aidl
package com.itis.template;

// Declare any non-default types here with import statements

interface ISongAidlInterface {

    int sum(int a, int b);

    void play();
    void pause();

    Song getSong();
    void setCurrentSong(in Song song);
    void setCurrentSongFromBundle(in Bundle bundle);
}

parcelable Song;
