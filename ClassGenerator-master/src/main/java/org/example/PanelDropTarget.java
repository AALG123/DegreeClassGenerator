package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

public class PanelDropTarget extends DropTarget {
    private JPanel panel;

    public PanelDropTarget(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public synchronized void drop(DropTargetDropEvent dtde) {
        try {
            String droppedPanelTitle = (String) dtde.getTransferable().getTransferData(DataFlavor.stringFlavor);
            Component[] components = panel.getComponents();
            for (Component component : components) {
                if (component instanceof DraggablePanel) {
                    DraggablePanel draggablePanel = (DraggablePanel) component;
                    if (draggablePanel.getName().equals(droppedPanelTitle)) {
                        panel.add(draggablePanel);
                        System.out.println("DraggablePanel added to PanelDropTarget"); // print statement
                        panel.revalidate();
                        panel.repaint();
                        dtde.dropComplete(true);
                        return;
                    }
                }
            }
            dtde.dropComplete(false);
        } catch (Exception e) {
            dtde.dropComplete(false);
        }
    }
}