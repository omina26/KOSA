package use_case.services;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface GetRecommendationAPIHandlerInterface {
    List<String> getRecommendation(String accessToken, List<String> seedTracks, double acousticness, double danceability, double energy, double instrumentalness, double liveness, double speechiness, double valence) throws IOException, InterruptedException;
}
