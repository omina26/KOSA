package app;

import entity.Playlist;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.group_playlist.GroupPlaylistPresenter;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import use_case.group_playlist.GroupPlaylistDataAccessInterface;
import use_case.group_playlist.GroupPlaylistInputBoundary;
import use_case.group_playlist.GroupPlaylistInteractor;
import use_case.group_playlist.GroupPlaylistOutputBoundary;
import use_case.group_playlist.services.GroupPlaylistAPIHandler;
import use_case.login.LoginDataAccessInterface;
import view.GroupPlaylistView;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class GroupPlaylistUseCaseFactory {

    private GroupPlaylistUseCaseFactory(){};
    public static GroupPlaylistView create(
            ViewManagerModel viewManagerModel,
            GroupPlaylistViewModel groupPlaylistViewModel,
            LoggedInViewModel loggedInViewModel,
            GroupPlaylistDataAccessInterface groupDataAccessObject,
            User user
    ){
        try {
            GroupPlaylistController groupPlaylistController = createGroupPlaylistUseCase(viewManagerModel, loggedInViewModel,
                    groupPlaylistViewModel, groupDataAccessObject, user);
            return new GroupPlaylistView(groupPlaylistController, groupPlaylistViewModel, loggedInViewModel.getState());
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not Access User Data");
        }
        return null;
    }
    private static GroupPlaylistController createGroupPlaylistUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            GroupPlaylistViewModel groupPlaylistViewModel,
            GroupPlaylistDataAccessInterface groupDataAccessObject,
            User user)
            throws IOException{
        GroupPlaylistOutputBoundary groupPlaylistOutputBoundary = new GroupPlaylistPresenter();
        GroupPlaylistAPIHandler groupPlaylistAPIHandler = new GroupPlaylistAPIHandler();
        GroupPlaylistInteractor groupPlaylistInteractor = new GroupPlaylistInteractor(groupDataAccessObject,
                groupPlaylistOutputBoundary, groupPlaylistAPIHandler);
        return new GroupPlaylistController(groupPlaylistInteractor, user);
    }

}
