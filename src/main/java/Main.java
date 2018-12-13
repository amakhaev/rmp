import com.rmp.widget.RMPWidgetBuilder;
import com.rmp.widget.skins.Skin;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        RMPWidgetBuilder builder = new RMPWidgetBuilder()
                .setSkin(Skin.DEFAULT);

        SwingUtilities.invokeLater(() -> {
            builder.build().showWidget();
        });
    }

}
