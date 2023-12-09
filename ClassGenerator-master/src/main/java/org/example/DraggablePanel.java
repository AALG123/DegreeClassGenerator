package org.example;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import javax.swing.*;

public class DraggablePanel extends JPanel {
    public DraggablePanel(LayoutManager layout) {
        super(layout);
        setTransferHandler(new PanelTransferHandler());
    }

    private class PanelTransferHandler extends TransferHandler {
        @Override
        protected Transferable createTransferable(JComponent c) {
            return new PanelTransferable(DraggablePanel.this);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }
    }

    private class PanelTransferable implements Transferable {
        private DraggablePanel panel;

        public PanelTransferable(DraggablePanel panel) {
            this.panel = panel;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{new DataFlavor(DraggablePanel.class, "Panel")};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return getTransferDataFlavors()[0].equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) {
            return panel;
        }
    }
}