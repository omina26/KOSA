package use_case.services;

import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public interface CreatePlaylistAPIHandlerInterface {
    public void createPlaylist(String accessToken, String userId, String playlistName, List<String> recommendations) throws IOException, InterruptedException;
}
