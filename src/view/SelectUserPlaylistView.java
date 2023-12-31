package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.select_user_playlist.SelectUserPlaylistState;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SelectUserPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Select User Playlists";
    public final SelectUserPlaylistsViewModel selectUserPlaylistsViewModel;

    final JLabel instruction;
    final JButton GET_GROUP_PLAYLIST;
    public JPanel allPlaylists;

    public ArrayList<String> selectedPlaylists;
    public ArrayList<JCheckBox> checkBoxes;
    private GroupPlaylistController groupPlaylistController;
    public SelectUserPlaylistView(SelectUserPlaylistsViewModel selectUserPlaylistsViewModel,
                                  GroupPlaylistController groupPlaylistController,
                                  ViewManagerModel viewManagerModel) {

        this.selectUserPlaylistsViewModel = selectUserPlaylistsViewModel;
        this.selectUserPlaylistsViewModel.addPropertyChangeListener(this);
        this.groupPlaylistController = groupPlaylistController;
        this.selectedPlaylists = new ArrayList<String>();

        JPanel buttons = new JPanel();
        allPlaylists = new JPanel();
        allPlaylists.setLayout(new BoxLayout(allPlaylists, BoxLayout.Y_AXIS));
        this.checkBoxes = new ArrayList<JCheckBox>();

        JLabel title = new JLabel("Select playlists to group");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        instruction = new JLabel("Please select which playlists you would like to add to the grouping:");


        GET_GROUP_PLAYLIST = new JButton("Get Group Playlist");
        buttons.add(GET_GROUP_PLAYLIST);

        GET_GROUP_PLAYLIST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectUserPlaylistState selectUserPlaylistState = selectUserPlaylistsViewModel.getState();
                groupPlaylistController.executeUseCase(selectedPlaylists, selectUserPlaylistState.getUserPlaylistsOnly(), selectUserPlaylistState.getUser(), selectUserPlaylistState.getNonUserPlaylistID());
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(instruction);
        this.add(allPlaylists);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SelectUserPlaylistState state = (SelectUserPlaylistState) evt.getNewValue();
        ArrayList<String> playlists = state.getAllPlaylists();
        ArrayList<String> names = state.getNames();
        System.out.println(playlists);
        for (String name: names){
            checkBoxes.add(new JCheckBox(name));
        }
        for (int i = 0; i < names.size(); i++){
            checkBoxes.get(i).setName(playlists.get(i));
        }
        for (JCheckBox checkBox: checkBoxes){
            allPlaylists.add(checkBox);
            checkBox.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (!checkBox.isSelected()){
                        selectedPlaylists.remove(checkBox.getName());
                    }
                    else{
                        if(!selectedPlaylists.contains(checkBox.getName())){
                            selectedPlaylists.add(checkBox.getName());
                        }
                    }
                    System.out.println(selectedPlaylists);
                }
            });
        }

    }

}

