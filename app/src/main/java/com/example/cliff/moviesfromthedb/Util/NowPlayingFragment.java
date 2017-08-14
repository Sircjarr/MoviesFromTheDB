package com.example.cliff.moviesfromthedb.Util;

import com.example.cliff.moviesfromthedb.UpcomingFragment;

public class NowPlayingFragment  extends UpcomingFragment {

    private final String nowPlayingURL = "https://api.themoviedb.org/3/movie/now_playing?api_key=787c4e4f4194b88bc3696eca661ecc82&language=en-US&page=1";

    @Override
    public String getCallURL() {
        return nowPlayingURL;
    }

}
