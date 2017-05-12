import interfaces.ImageContextMenuListener;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;


public class ImageContextMenu extends ContextMenu {
    private ImageView imv;
    private ImageContextMenuListener listener;
    private ContextMenu contextMenu;

    public ImageContextMenu(final ImageView imv) {
        MenuItem menuItem = new MenuItem("Показать фотографию");
        menuItem.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.showPicture(imv);
            }
        });
        contextMenu = new ContextMenu(menuItem);
        this.imv = imv;

        this.imv.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown() && imv != null) {
                contextMenu.show(imv.getScene().getWindow(), mouseEvent.getSceneX(), mouseEvent.getSceneY());
            }
        });
    }

    public void setListener(ImageContextMenuListener list) {
        if (list != null) {
            this.listener = list;
        }
    }
}
