package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.group_playlist.GroupPlaylistPresenter;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;
import use_case.group_playlist.GroupPlaylistDataAccessInterface;
import use_case.group_playlist.GroupPlaylistInteractor;
import use_case.group_playlist.GroupPlaylistOutputBoundary;
import use_case.services.GroupPlaylistAPIHandler;
import view.GroupPlaylistView;

import javax.swing.*;
import java.io.IOException;

public class GroupPlaylistUseCaseFactory {

    private GroupPlaylistUseCaseFactory(){};
    public static GroupPlaylistView create(
            ViewManagerModel viewManagerModel,
            GroupPlaylistViewModel groupPlaylistViewModel,
            LoggedInViewModel loggedInViewModel,
            GroupPlaylistDataAccessInterface groupDataAccessObject,
            SelectUserPlaylistsViewModel selectUserPlaylistsViewModel,
            GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel
    ){
        try {
            GroupPlaylistController groupPlaylistController = createGroupPlaylistUseCase(viewManagerModel, loggedInViewModel,
                    groupPlaylistViewModel, groupDataAccessObject, selectUserPlaylistsViewModel,
                    groupPlaylistCreatedViewModel);
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
            SelectUserPlaylistsViewModel selectUserPlaylistsViewModel,
            GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel)
            throws IOException{
        GroupPlaylistOutputBoundary groupPlaylistOutputBoundary = new GroupPlaylistPresenter(viewManagerModel,
                groupPlaylistViewModel, selectUserPlaylistsViewModel, groupPlaylistCreatedViewModel);
        GroupPlaylistAPIHandler groupPlaylistAPIHandler = new GroupPlaylistAPIHandler();
        GroupPlaylistInteractor groupPlaylistInteractor = new GroupPlaylistInteractor(groupDataAccessObject,
                groupPlaylistOutputBoundary, groupPlaylistAPIHandler);
        return new GroupPlaylistController(groupPlaylistInteractor);
    }

}
