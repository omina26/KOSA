package use_case.analyze_playlist;

import data_access.login.UserDataAccessObject;
import entity.User;
import interface_adapter.logged_in.LoggedInViewModel;
import org.apache.commons.logging.Log;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginOutputBoundary;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class AnalyzePlaylistInteractor implements AnalyzePlaylistInputBoundary {

    final AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject;
    final AnalyzePlaylistOutputBoundary analyzePlaylistPresenter;

    final LoginDataAccessInterface userDataAccessObject;


    //final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    //final String CLIENT_SECRET = "6f843aec369846528a70b621a721e788";
    //static final String REDIRECT_URI = "http://localhost:8888/callback";
    final String CLIENT_ID = "1c6992172cb240fd85caa34bae033b94";
    final String ACCESS_TOKEN = "";
    final String PLAYLIST_ID = "";
    final String endpoint = "https://api.spotify.com/v1/playlists/" + PLAYLIST_ID;




    public AnalyzePlaylistInteractor(LoginDataAccessInterface userDataAccessObject,
                                     AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                     AnalyzePlaylistOutputBoundary analyzePlaylistPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.analyzePlaylistDataAccessObject = analyzePlaylistDataAccessObject;
        this.analyzePlaylistPresenter = analyzePlaylistPresenter;
    }

    User user = userDataAccessObject.getCurrentUser();

    private String getPlaylistItems() {

    }

    @Override
    public void execute(AnalyzePlaylistInputData analyzePlaylistInputData) {
        String playlistID = analyzePlaylistInputData.getPlaylistID();

        analyzePlaylistDataAccessObject.savePlaylistID(playlistID);
    }
}
